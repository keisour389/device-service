package com.smart_physio_therapy.device_service.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {
    @Value("${mqtt.host}")
    private String host;
    @Value("${mqtt.port}")
    private String port;
    @Value("${mqtt.clientId}")
    private String clientId;
    @Value("${mqtt.topic}")
    private String topic;

    @Bean
    public MqttClient configMqttClient() throws MqttException {
        MqttClient client = new MqttClient(host + ":" + port, clientId, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        client.connect(options);
        client.subscribe(topic, (t, msg) -> {
            String payload = new String(msg.getPayload());
            System.out.println("Received: " + payload);
            // TODO: Message handler, save data in Mongo and send to Kafka will do later
        });

        return client;
    }
}
