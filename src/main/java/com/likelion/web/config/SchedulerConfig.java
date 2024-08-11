// package com.likelion.web.config;

// import org.springframework.batch.core.JobExecution;
// import org.springframework.batch.core.JobParameters;
// import org.springframework.batch.core.launch.JobLauncher;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.scheduling.annotation.EnableAsync;
// import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

// @Configuration
// @EnableAsync
// public class SchedulerConfig {

//     @Autowired
//     private JobLauncher jobLauncher;

//     @Autowired
//     private Job logJob;

//     @Bean
//     ThreadPoolTaskScheduler taskScheduler() {
//         ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//         scheduler.setPoolSize(10);
//         scheduler.setThreadNamePrefix("scheduled-task-");
//         scheduler.initialize();
//         return scheduler;
//     }

//     // @Scheduled(fixedRate = 5000) // 300000 milliseconds = 5 minutes setup time big will error shutdown VM because running out of memory
//     // @Async
//     public void runJob() throws Exception {
//         System.out.println("Starting the batch job");
//         try {
//             JobExecution execution = jobLauncher.run(logJob, new JobParameters());
//             System.out.println("Job Status : " + execution.getStatus());
//             System.out.println("Job completed");
//         } catch (Exception e) {
//             e.printStackTrace();
//             System.out.println("Job failed");

//         }
//     }
// }
