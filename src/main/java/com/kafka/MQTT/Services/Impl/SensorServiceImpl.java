package com.kafka.MQTT.Services.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.MQTT.Model.SensorData;
import com.kafka.MQTT.Repository.SensorRepository;
import com.kafka.MQTT.Services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl implements SensorService {


    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Autowired
    private SensorRepository sensorRepository;


    @Override
    public void save(String data) throws Exception {
        System.out.println(data);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(data);
        // Set fields from JSON string or parse JSON string and set fields accordingly
        SensorData sensorData = new SensorData();

        sensorData.setAltitude(jsonNode.get("altitude").asText());
        sensorData.setDate(jsonNode.get("date").asText());
        sensorData.setNote(jsonNode.get("note").asText());
        sensorData.setPressure(jsonNode.get("pressure").asText());
        sensorData.setStatus(jsonNode.get("status").asInt());
        sensorData.setType(jsonNode.get("type").asText());
        sensorData.setTemperature(jsonNode.get("temperature").asText());
        sensorData.setTime(jsonNode.get("time").asText());
        sensorData.setSoilmositure(jsonNode.get("soilmiosture").asText());
        sensorRepository.save(sensorData);
    }
}
