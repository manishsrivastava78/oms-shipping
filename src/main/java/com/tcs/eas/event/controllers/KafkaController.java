package com.tcs.eas.event.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.eas.event.engine.Producer;
import com.tcs.eas.event.model.Order;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@Valid @RequestBody Order order) {
    	ObjectMapper mapper = new ObjectMapper();
    	String json = "";
    	try {
    	  json = mapper.writeValueAsString(order);
    	  System.out.println("ResultingJSONstring = " + json);
    	  //System.out.println(json);
    	} catch (JsonProcessingException e) {
    	   e.printStackTrace();
    	} 
        this.producer.sendMessageToShipmentTopic(json);
        
        //this.producer.sendMessageToMailTopic(json);
    }
}
