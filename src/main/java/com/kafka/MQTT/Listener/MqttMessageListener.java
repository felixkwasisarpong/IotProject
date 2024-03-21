package com.kafka.MQTT.Listener;

import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MqttMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MqttMessageListener.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${mqtt.broker.url}")
    private String mqttBrokerUrl;

    @Value("${mqtt.topic}")
    private String mqttTopic;

    private MqttClient client;

    @PostConstruct
    public void init() {
        try {
            client = new MqttClient(mqttBrokerUrl, MqttClient.generateClientId());
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    logger.error("Connection to MQTT broker lost. Attempting to reconnect...", cause);
                    reconnect();
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String payload = new String(message.getPayload());
                    kafkaTemplate.send(mqttTopic, payload);
                 //   logger.info("Received message from MQTT and sent to Kafka: {}", payload);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {}
            });

            connect();
            client.subscribe(mqttTopic);
            logger.info("Subscribed to MQTT topic: {}", mqttTopic);
        } catch (MqttException e) {
            logger.error("Failed to initialize MQTT client.", e);
            throw new RuntimeException("Failed to initialize MQTT client", e);
        }
    }

    private void connect() throws MqttException {
        client.connect();
    }

    private void reconnect() {
        try {
            // Wait for a short duration before attempting to reconnect
            Thread.sleep(5000); // 5 seconds
            connect();
            client.subscribe(mqttTopic);
            logger.info("Reconnected to MQTT broker and subscribed to topic: {}", mqttTopic);
        } catch (InterruptedException | MqttException e) {
            logger.error("Failed to reconnect to MQTT broker.", e);
            // You may choose to retry reconnecting here or handle the error accordingly
        }
    }

}
