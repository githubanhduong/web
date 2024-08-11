// package com.likelion.web.config;

// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

// import org.springframework.batch.core.Job;
// import org.springframework.batch.core.Step;
// // import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
// import org.springframework.batch.core.job.builder.JobBuilder;
// import org.springframework.batch.core.repository.JobRepository;
// import org.springframework.batch.core.step.builder.StepBuilder;
// import org.springframework.batch.core.step.tasklet.Tasklet;
// import org.springframework.batch.repeat.RepeatStatus;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.transaction.PlatformTransactionManager;

// @Configuration
// // @EnableBatchProcessing //batch v5 not use
// public class BatchConfiguration {

//     @Bean
//     Job myJob(JobRepository jobRepository, Step step) {
//         return new JobBuilder("myJob", jobRepository)
//                 .start(step)
//                 .build();
//     }

//     @Bean
//     Step stepTest(JobRepository jobRepository , PlatformTransactionManager transactionManage) {
//         var step = new StepBuilder("stepTest", jobRepository)
//                 .tasklet(taskletTest(), transactionManage)
//                 .build();
//         return step;
//     }

//     @Bean
//     Tasklet taskletTest() {
//         return (contribution, chunkContext) -> {
//             // Log the current time
//             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//             String timestamp = LocalDateTime.now().format(formatter);
//             System.out.println("Logging at: " + timestamp);
//             return RepeatStatus.FINISHED;
//         };
//     }
// }
