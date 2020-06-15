package edu.intensive;

import edu.intensive.config.KafkaProcessor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler {
    @StreamListener(KafkaProcessor.INPUT)
    public void onEvent(@Payload String message) {
    }

}
