package org.pcloud.batchscheduler;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {
    @Bean
    public JobDetail quartzJobDetail() {
        return JobBuilder.newJob(BatchJobBean.class)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger jobTrigger() {
        SimpleScheduleBuilder sb = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMilliseconds(5).withRepeatCount(4);
        return TriggerBuilder.newTrigger()
                .forJob(quartzJobDetail())
                .withSchedule(sb)
                .build();
    }
}
