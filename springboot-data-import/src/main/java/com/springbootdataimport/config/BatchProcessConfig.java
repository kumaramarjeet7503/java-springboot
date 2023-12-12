package com.springbootdataimport.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.springbootdataimport.model.OrderImport;

@Configuration
@EnableBatchProcessing
public class BatchProcessConfig {
    

    @Bean
public Job importUserJob(JobRepository jobRepository,Step step1, JobCompletionNotificationListener listener) {
  return new JobBuilder("importOrderJob", jobRepository)
    .listener(listener)
    .start(step1)
    .build();
}

@Bean
public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
          FlatFileItemReader<OrderImport> reader, OrderItemProcessor processor, JdbcBatchItemWriter<OrderImport> writer) {
  return new StepBuilder("step1", jobRepository)
    .<OrderImport, OrderImport> chunk(3, transactionManager)
    .reader(reader)
    .processor(processor)
    .writer(writer)
    .build();
}

@Bean
public FlatFileItemReader<OrderImport> reader() {
  return new FlatFileItemReaderBuilder<OrderImport>()
    .name("orderItemReader")
    .resource(new ClassPathResource("resource.csv"))
    .delimited()
    .names("Id","Street","Name","Roll","Balance","Amount","Commision","City","Item","Tax")
    .targetType(OrderImport.class)
    .build();
}

@Bean
public OrderItemProcessor processor() {
  return new OrderItemProcessor();
}

@Bean
public JdbcBatchItemWriter<OrderImport> writer(DataSource dataSource) {
  return new JdbcBatchItemWriterBuilder<OrderImport>()
    .sql("insert into order_import(id, street,name,roll,balance,amount,commision,city,item,tax) values (:Id,:Street,:Name,:Roll,:Balance,:Amount,:Commision,:City,:Item,:Tax)")
    .dataSource(dataSource)
    .beanMapped()
    .build();
}

}
