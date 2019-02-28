package com.mimihaisuper.licenta.config;

import com.mimihaisuper.licenta.services.PersistenceService;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfiguration {
    private static final String TOPIC = "esp/#";

    @Autowired
    PersistenceService persistenceService;

    @Bean
    public void getPing() throws MqttException {
        IMqttClient mqttClient = new MqttClient("tcp://localhost:1883", "RaspPi");
        mqttClient.connect();
        mqttClient.subscribe(TOPIC, (TOPIC, msg) -> {
            System.out.println(msg);
            persistenceService.postSensorMessage(msg.toString(), "MQ135");
            Thread.sleep(2000);
        });
    }
}
