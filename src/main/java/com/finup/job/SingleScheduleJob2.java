package com.finup.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by furuitao on 2017/12/4.
 */
@Component
public class SingleScheduleJob2 {

    @Scheduled(cron = "0/4 * * * * ?")
    public void single(){
        System.out.println(Thread.currentThread().getName() + new Date() + "I am Single2 s");
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "1/4 * * * * ?")
    public void single1(){
        System.out.println(Thread.currentThread().getName() + new Date() + "I am Single3 s");
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
