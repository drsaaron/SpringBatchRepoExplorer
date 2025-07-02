/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.springbatchrepoexplorer;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
@Slf4j
public class ListJobExecutionsCommandLineRunner implements CommandLineRunner {

    @Autowired
    private JobExplorer jobExplorer;

    // sort executions in descending order by ID so that the most recent is first.
    // shoulnd't be necessary as the repo seems to do so, but be explicit
    private static final Comparator<JobExecution> JOB_EXEC_COMPARATOR = (ex1, ex2) -> ex2.getId().compareTo(ex1.getId());

    // log the execution details
    private static final Consumer<JobExecution> JOB_EXECUTION_LOGGER
            = execution -> log.info("found job execution {}: jobInstance {}, startDate {}, endDate {}, status {}",
                    execution.getId(), execution.getJobInstance().getId(), execution.getStartTime(), execution.getEndTime(),
                    execution.getExitStatus().getExitCode());
    
    // for each job, get the instances and log the executions for each instance
    private void logJobExecutionDetails(String jobName) {
        jobExplorer.getJobInstances(jobName, 0, 100)
                .stream()
                .map(instance -> jobExplorer.getJobExecutions(instance))
                .flatMap(executions -> executions.stream())
                .sorted(JOB_EXEC_COMPARATOR)
                .forEach(JOB_EXECUTION_LOGGER);
    }

    @Override
    public void run(String... args) throws Exception {
        
        log.info("listing all job executions");
        
        List<String> jobs = jobExplorer.getJobNames();
        
        // dump details grouped by job
        jobs.stream()
                .peek(name -> log.info("looking at job {}", name))
                .forEach(jobName -> logJobExecutionDetails(jobName));
    }

}
