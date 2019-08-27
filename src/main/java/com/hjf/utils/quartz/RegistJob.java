package com.hjf.utils.quartz;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Date;


/**
 * 该定时器,适用简单没并发的定时任务(只有有一个线程执行)
 *
 * 这个定时器全部就这个方法,有极高的隐蔽性,不需要引入独立jar包
 * 百度云有Quartiz 代码,没有集成到spring项目中;
 */
@Configuration
@EnableScheduling
public class RegistJob implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addCronTask(()->{
            System.out.println("定时器="+new Date()+Thread.currentThread().getId());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"0/1 * * * * ?");

        scheduledTaskRegistrar.addCronTask(()->{
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("定时器2="+new Date()+Thread.currentThread().getId());
        },"0/2 *  * * * ?");


    }
}
