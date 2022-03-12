package com.muni.test.calledservice;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/called-service")
public class CalledServiceController {
	
	private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(CalledServiceController.class);
	
	@Value("${my.env.name}")
	private String prop;
	
	private static final String MY_KAFKA_TOPIC = "FIRST_TOPIC_ON_KAFKA";
	
	@Autowired
    KafkaTemplate<String, String> kafkaTemplate;

	@GetMapping(value="/{id}", produces="text/plain")
	public String getImpl(@PathVariable int id) {
		log.info(prop);
		return MessageFormat.format("Called Service Invoked for ID: {0}", id+1000);
	}
	
	@GetMapping(value="/kafka/{id}", produces="text/plain")
	public String sendToKafka(@PathVariable int id) {
		
		kafkaTemplate.send(MY_KAFKA_TOPIC, String.format("Message on to topic %s thru spring-boot api with message ID: %s ", MY_KAFKA_TOPIC, id));
		log.info("Message has been sent to Kafka...!");
		return "message sent!";
	}
}
