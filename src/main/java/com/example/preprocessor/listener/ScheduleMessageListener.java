
package com.example.preprocessor.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//import com.example.ingestor.model.Schedule;
import com.protobuf.schedule.ScheduleProto;


@Component
public class ScheduleMessageListener {
	
	@KafkaListener(topics="${message.topic.name}")
	public void listenCustomEvent(ScheduleProto.Schedule payload) {
		System.out.println(
				"Recived Schedule Message ---> " + payload.toString());
	}
}
