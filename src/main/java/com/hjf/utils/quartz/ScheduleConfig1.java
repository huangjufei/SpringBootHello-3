package com.hjf.utils.quartz;

import org.apache.logging.log4j.LogManager;
import org.quartz.*;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;

/**
 * 该定时器使用于有并发任务的
 * 需要导入spring-boot-starter-quartz 的jar包
 * SpringBoot整合了Quartz，而且使用起来比Quartz简单许多，
 * 下面代码来至下面这个url
 * https://www.jianshu.com/p/61e3abc22fbd
 */
@Configuration
public class ScheduleConfig1 {

    @Bean
    public JobDetail task1JobDetail() {
        return JobBuilder.newJob(Task1.class)
                .withIdentity("task1")
                .storeDurably(true)
                .build();
    }

    @Bean
    public JobDetail task2JobDetail() {
        return JobBuilder.newJob(Task2.class)
                .withIdentity("task2")
                .storeDurably(true)
                .build();
    }

    @Bean
    public Trigger task1Trigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/40 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(task1JobDetail())
                .withIdentity("task1")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger task2Trigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/40 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(task2JobDetail())
                .withIdentity("task2")
                .withSchedule(scheduleBuilder)
                .build();
    }
}