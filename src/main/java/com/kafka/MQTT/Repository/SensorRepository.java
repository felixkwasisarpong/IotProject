package com.kafka.MQTT.Repository;

import com.kafka.MQTT.Model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<SensorData, Long> {
}
