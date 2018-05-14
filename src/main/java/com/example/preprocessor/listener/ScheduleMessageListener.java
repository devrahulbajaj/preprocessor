
package com.example.preprocessor.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.preprocessor.model.Schedule;


@Component
public class ScheduleMessageListener {
	
	@KafkaListener(topicPartitions = @TopicPartition(topic = "schedule", partitionOffsets = {
			@PartitionOffset(partition = "1", initialOffset = "0") }), containerFactory = "scheduleMessageKafkaListenerContainerFactory")
	public void listenCustomEvent(@Payload Schedule event,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partitionKey) {

		System.out.println(
				"Recived Schedule Message ---> " + event.getFlightNumber() + " From Partition Key " + partitionKey);

	}
}
