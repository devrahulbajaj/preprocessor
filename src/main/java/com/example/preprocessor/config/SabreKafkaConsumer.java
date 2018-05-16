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
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import com.example.preprocessor.deserializer.ScheduleDeserializer;
import com.protobuf.schedule.ScheduleProto;

@EnableKafka
@Configuration
public class SabreKafkaConsumer {

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;
	
	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ScheduleDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "schdMsg");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		return props;
	}

	@Bean
    public ConsumerFactory<String, ScheduleProto.Schedule> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }
	
	@Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ScheduleProto.Schedule>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ScheduleProto.Schedule> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
