package com.smart_physio_therapy.device_service.config;

import com.smart_physio_therapy.device_service.mqtt.router.SensorRouter;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MqttConfig {
    @Value("${mqtt.host}")
    private String host;
    @Value("${mqtt.port}")
    private String port;
    @Value("${mqtt.clientId}")
    private String clientId;
    @Value("${mqtt.topic}")
    private String topic;

    private static final Logger log = LoggerFactory.getLogger(MqttConfig.class);

    private final SensorRouter sensorRouter;

    @Bean
    public MqttClient configMqttClient() throws MqttException {
        MqttClient client = new MqttClient(host + ":" + port, clientId, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        client.connect(options);
        client.subscribe(topic, (t, msg) -> {
            log.debug("[Mqtt]: System has been received device data from MQTT");
            String payload = new String(msg.getPayload());
            sensorRouter.routeAndHandle(payload);
        });

        return client;
    }
}
