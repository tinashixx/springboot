package com.bharath.boot.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.bharath.boot.batch.model.Product;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

   @Autowired
   private JobBuilderFactory jbf;

   @Autowired
   private StepBuilderFactory sbf;


   @Bean
   public Step step() {
      return sbf.get("s16")
            .<Product,Product>chunk(1)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .build();

   }

   @Bean
   public Job job() {
      return jbf.get("j16")
            .incrementer(new RunIdIncrementer())
            .start(step())
            .build();

   }



   @Bean
   ItemReader<Product> reader() {
      FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
      reader.setResource(new PathResource(".\\src\\main\\java\\products.csv"));
      //reader.setResource(new PathResource(".\\src\\main\\java\\products.csv"));
      DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
      DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
      lineTokenizer.setNames("id","name", "description","price");
      BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
      fieldSetMapper.setTargetType(Product.class);
      lineMapper.setLineTokenizer(lineTokenizer);
      lineMapper.setFieldSetMapper(fieldSetMapper);
      reader.setLineMapper(lineMapper);
      return reader;
   }

   @Bean
   public ItemProcessor<Product,Product> processor() {
      return p -> {
         p.setPrice(p.getPrice() * 0.9);
         return  p;
      };
   }

   @Bean
   public ItemWriter<Product> writer() {
      JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
      writer.setDataSource(dataSource());
      writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
      writer.setSql("INSERT INTO PRODUCT(ID,NAME,DESCRIPTION,PRICE) VALUES(:id,:name,:description,:price)");

      return  writer;
   }

   @Bean
   public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
      dataSource.setUsername("root");
      dataSource.setPassword("test1234");

      return  dataSource;
   }




}
