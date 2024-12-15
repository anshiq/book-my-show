//package com.bookMyShow.bookmyshow.BatchConfig;
//
//import com.bookMyShow.bookmyshow.entity.Movie;
//import com.bookMyShow.bookmyshow.entity.MovieElastic;
//import com.bookMyShow.bookmyshow.repository.MovieElasticRepository;
//import com.bookMyShow.bookmyshow.repository.MovieRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.data.RepositoryItemReader;
//import org.springframework.batch.item.data.RepositoryItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.Collections;
//
//@Configuration
//@EnableBatchProcessing
//@RequiredArgsConstructor
//public class SpringBatchConfig {
//
//    private final MovieRepository movieRepository;
//    private final MovieElasticRepository movieElasticRepository;
//    private final RedisTemplate<String, String> redisTemplate;
//
//    @Bean
//    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
//        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setTransactionManager(transactionManager);
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//
//
//    @Bean
//    public RepositoryItemReader<Movie> reader() {
//        RepositoryItemReader<Movie> reader = new RepositoryItemReader<>();
//        reader.setRepository(movieRepository);
//        reader.setMethodName("findAll");
//        reader.setName("movieReader");
//        reader.setSort(Collections.singletonMap("lastUpdated", Sort.Direction.ASC));
//
//        return reader;
//    }
//
//    @Bean
//    public RepositoryItemWriter<MovieElastic> writer() {
//        RepositoryItemWriter<MovieElastic> writer = new RepositoryItemWriter<>();
//        writer.setRepository(movieElasticRepository);
//        writer.setMethodName("save");
//        return writer;
//    }
//
//    @Bean
//    public MovieProcessor movieProcessor() {
//        return new MovieProcessor(redisTemplate);
//    }
//
//    @Bean
//    public Step step1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("step1", jobRepository)
//                .<Movie, MovieElastic>chunk(3, platformTransactionManager)
//                .reader(reader())
//                .processor(movieProcessor())
//                .writer(writer())
//                .build();
//    }
//
//    @Bean
//    public Job job(JobRepository jobRepository, Step step1) {
//        return new JobBuilder("myJob", jobRepository)
//                .start(step1)
//                .build();
//    }
//
//
//}
