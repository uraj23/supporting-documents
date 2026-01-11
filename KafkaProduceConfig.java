package com.codeur.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.codeur.constant.AppConstants;
import com.codeur.entity.Order;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

@Configuration
public class KafkaProduceConfig {
	
	@Bean
	public ProducerFactory<String, Order> producerFactory(){
		
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.HOST);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializable.class);
		
		return new DefaultKafkaProducerFactory<>(configProps);
		
	}
	
	@Bean
	public KafkaTemplate<String, Order> kafkaTemplet(){
		return new KafkaTemplate<>(producerFactory());
	}
	

}
