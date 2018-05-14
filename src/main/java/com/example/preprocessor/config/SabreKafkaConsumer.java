package com.example.preprocessor.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.preprocessor.model.Schedule;

@EnableKafka
@Configuration
public class SabreKafkaConsumer {

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

//	public ConsumerFactory<String, String> consumerFactory(String groupId) {
//		Map<String, Object> props = new HashMap<>();
//		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		return new DefaultKafkaConsumerFactory<>(props);
//	}

	public ConsumerFactory<String, Schedule> scheduleMessageConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "schdMsg");
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
				new JsonDeserializer<>(Schedule.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Schedule> scheduleMessageKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Schedule> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(scheduleMessageConsumerFactory());
		return factory;
	}

}
