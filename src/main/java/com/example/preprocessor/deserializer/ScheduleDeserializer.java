package com.example.preprocessor.deserializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.protobuf.schedule.ScheduleProto;

public class ScheduleDeserializer implements Deserializer<ScheduleProto.Schedule> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ScheduleProto.Schedule deserialize(String topic, byte[] data) {
		try {
			return ScheduleProto.Schedule.parseFrom(data);
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Recieved some exception" + e.getMessage(),e);
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	

}
