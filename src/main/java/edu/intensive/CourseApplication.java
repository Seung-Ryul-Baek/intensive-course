package edu.intensive;

import edu.intensive.config.KafkaProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class CourseApplication {
	public static ApplicationContext applicationContext;
	public static void main(String[] args) {

		applicationContext = SpringApplication.run(CourseApplication.class, args);
	}

}
