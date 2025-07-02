/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.springbatchrepoexplorer;

import com.blazartech.batch.IJobParametersBuilder;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author scott
 */
@Configuration
@Slf4j
public class BatchJobConfiguration {
    
    @Bean
    public Map<String, Job> batchJobMap() {
        log.info("getting batchJobMap");
        return new HashMap<>();
    }
    
    @Bean
    public Map<String, IJobParametersBuilder> batchJobParameterBuilderMap() {
        log.info("making param builder map");
        return new HashMap<>();
    }
    
    public ExecutionContextSerializer jacksonSerializer() {
        return new Jackson2ExecutionContextStringSerializer();
    }

}
