package org.pcloud.batchscheduler;

import lombok.RequiredArgsConstructor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

/*
    QuartzJobBean
    - 배치 기능을 재공하는 클래스
    executeInternal Method
    - 스케줄링된 이벤트가 발생할 때 호출되는 메서드
 */

@RequiredArgsConstructor
public class BatchJobBean extends QuartzJobBean {
    private final Job job;
    private final JobExplorer jobExplorer;
    private final JobLauncher jobLauncher;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobParameters jobParameters = new JobParametersBuilder(this.jobExplorer)
                .getNextJobParameters(this.job)
                .toJobParameters();
        try {
            this.jobLauncher.run(this.job, jobParameters);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
