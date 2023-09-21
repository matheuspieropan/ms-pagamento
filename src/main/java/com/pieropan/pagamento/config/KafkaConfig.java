package com.pieropan.pagamento.config;


import com.pieropan.pagamento.entities.PedidoEntity;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Autowired
    public KafkaProperties properties;

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> map = new HashMap<>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(map);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PedidoEntity> pedidoListenerFactory() {
        var configs = new ConcurrentKafkaListenerContainerFactory<String, PedidoEntity>();
        configs.setConsumerFactory(consumerFactory());

        return configs;
    }

    ConsumerFactory<String, PedidoEntity> consumerFactory() {
        JsonDeserializer<PedidoEntity> deserializer = new JsonDeserializer<>(PedidoEntity.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        map.put(ConsumerConfig.GROUP_ID_CONFIG, "pedido_id");

        return new DefaultKafkaConsumerFactory<>(map, new StringDeserializer(), deserializer);
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> map = new HashMap<>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());

        return new KafkaAdmin(map);
    }

    @Bean
    public NewTopic criarTopic() {
        return new NewTopic("pedido-pagamento-topic", 2, Short.parseShort("1"));
    }
}