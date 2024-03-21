package com.kafka.MQTT.Listener;

import com.kafka.MQTT.Services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class PostgresListener {
    @Autowired
    private SensorService sensorService;

    public PostgresListener(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapServers;
    @KafkaListener(topicPattern = "${mqtt.topic}", groupId = "${spring.kafka.consumer.group_id}")
    void consume(String message,@Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws Exception {
        sensorService.save(message);
    }
}
