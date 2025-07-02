/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.springbatchrepoexplorer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
@Slf4j
public class SerializerApplicationListener {
    
    @Autowired
    private Jackson2ExecutionContextStringSerializer jacksonSerializer;
    
    @Autowired(required = false) 
    private ObjectMapper objectMapper;
    
    @PostConstruct
    public void initializeObjectMapper() {
        if (objectMapper == null) {
            log.info("no object mapper, so creating one");
            objectMapper = new ObjectMapper();
        } else {
            log.info("using existing object mapper");
        }
    }
        
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent e) {
        log.info("onApplicationEvent: {}", e);
        
        objectMapper.registerModule(new JavaTimeModule());
        jacksonSerializer.setObjectMapper(objectMapper);
    }
}
