/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.blazartech.springbatchrepoexplorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author scott
 */
@SpringBootApplication(exclude = {
    BatchAutoConfiguration.class
})
@ComponentScan(basePackages = "com.blazartech")
public class SpringBatchRepoExplorer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchRepoExplorer.class, args);
    }
}
