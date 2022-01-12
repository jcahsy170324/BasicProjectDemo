package com.lg.bsp.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName Task1
 * @Description 定时任务Demo
 * @Author jincheng
 * @Date 2022/1/12 10:59
 * @Version 1.0
 **/
@Component
public class Task1 {

    @Scheduled(cron = "0 */1 * * * ?")
    public void showTime(){
        System.out.println("执行时间为:"+new Date().toString());
    }
}
