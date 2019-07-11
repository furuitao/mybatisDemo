package com.finup.job;

import com.finup.dynamic.Constants;
import com.finup.dynamic.ScheduleService;
import com.finup.dynamic.dto.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by furuitao on 2017/11/30.
 */
//把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
@Component
public class ScheduleJobInit {

    @Autowired
    private ScheduleService scheduleService;

    @PostConstruct
    public void initJob() {
//        ScheduleDTO scheduleDTO = new ScheduleDTO();
//        scheduleDTO.setScenarioId("firstName");
//        scheduleDTO.setJobGroup("firstGroup");
//        scheduleDTO.setTriggerType(Constants.QZ_CRON_TRIGGER);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put(Constants.NAME_KEY, "张三");
//        scheduleDTO.setJobDataMap(map);
//        scheduleDTO.setCronExpression("0/10 * * * * ?");
//        scheduleService.addJob(scheduleDTO);
//        scheduleDTO = new ScheduleDTO();
//        scheduleDTO.setScenarioId("secondName");
//        scheduleDTO.setJobGroup("firstGroup");
//        scheduleDTO.setTriggerType(Constants.QZ_CRON_TRIGGER);
//        map = new HashMap<String, Object>();
//        map.put(Constants.NAME_KEY, "李四");
//        scheduleDTO.setJobDataMap(map);
//        scheduleDTO.setCronExpression("0/5 * * * * ?");
//        scheduleService.addJob(scheduleDTO);
    }


}
