package com.finup.dynamic;

import org.quartz.*;

import java.util.Date;

/**
 * Created by furuitao on 2017/12/3.
 */
public class MessageJobFactory implements Job{

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String o = (String) jobDataMap.get(Constants.NAME_KEY);
        System.out.printf("%s发送消息给【%s】\n", new Date().toString(), o);
    }
}
