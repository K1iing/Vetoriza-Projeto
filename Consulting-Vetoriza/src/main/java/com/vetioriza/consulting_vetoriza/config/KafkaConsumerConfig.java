package com.vetioriza.consulting_vetoriza.config;

import com.vetioriza.consulting_vetoriza.dto.consulting.ConsultDTO;
import com.vetioriza.consulting_vetoriza.dto.consulting.UpdateStatusTratamentoDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, ConsultDTO> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "consult-patient-medical");
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);
        configProps.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "*");
        configProps.put(JacksonJsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        configProps.put(JacksonJsonDeserializer.VALUE_DEFAULT_TYPE, "com.vetioriza.consulting_vetoriza.dto.consulting.ConsultDTO");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ConsultDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ConsultDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}