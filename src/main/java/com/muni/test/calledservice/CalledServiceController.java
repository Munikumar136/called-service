package com.muni.test.calledservice;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/called-service")
public class CalledServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(CalledServiceController.class);
	
	@Value("${my.env.name}")
	private String prop;

	@GetMapping(value="/{id}", produces="text/plain")
	public String getImpl(@PathVariable int id) {
		logger.info(prop);
		return MessageFormat.format("Called Service Invoked for ID: {0}", id+1000);
	}
	
}
