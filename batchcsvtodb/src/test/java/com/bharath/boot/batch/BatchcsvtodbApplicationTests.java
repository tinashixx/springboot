package com.bharath.boot.batch;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BatchcsvtodbApplicationTests {

   @Autowired
   private JobLauncher jobLauncher;

   @Autowired
   private Job job;


   @Test
   public void testBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException,
         JobInstanceAlreadyCompleteException {
      JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
      jobLauncher.run(job,jobParameters);
   }
}
