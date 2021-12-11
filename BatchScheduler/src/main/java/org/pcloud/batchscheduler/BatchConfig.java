package org.pcloud.batchscheduler;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BatchConfig {
    @Autowired
    Scheduler scheduler;

    @PostConstruct
    public void init() throws SchedulerException {
        JobDetail heartBeatBean = quartzJobDetail(HeartBeatJobBean.class, new HashMap());
        scheduler.scheduleJob(heartBeatBean, cronTrigger("* * * * * * ?"));

    }

    private JobDetail quartzJobDetail(Class c, Map params) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);
        return JobBuilder.newJob(c)
                .usingJobData(jobDataMap)
                .build();
    }

    /**
     * CronSchedule 트리거 생성 메서드
     * @param scheduleCron Cron 포맷의 문자열
     * @return trigger
     */
    private Trigger cronTrigger(String scheduleCron) {
        return TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleCron))
                .build();
    }

    private Trigger simpleTrigger() {
        return TriggerBuilder.newTrigger()
                .startAt(DateBuilder.evenHourDateAfterNow())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1))
                .build();
    }
}
