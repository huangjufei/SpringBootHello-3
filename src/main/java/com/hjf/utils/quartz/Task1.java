package com.hjf.utils.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
//这个注解是使得 quartz 的单任务串行执行，多任务并行执行的关键
@DisallowConcurrentExecution
public class Task1 extends QuartzJobBean {

    private final static Logger log = LoggerFactory.getLogger(Task1.class);
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.error("我是task1111，我将执行10s钟， 线程名字 == > {} , 现在时间为 == > {}", Thread.currentThread().getId(),new Date());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.error("我是task1111，我已经执行完成了，线程名字 == > {} , 现在时间为 == > {}",Thread.currentThread().getId(),new Date());
    }
}