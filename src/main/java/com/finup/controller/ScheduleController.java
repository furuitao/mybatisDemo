package com.finup.controller;

import com.finup.dynamic.ScheduleException;
import com.finup.dynamic.ScheduleService;
import com.finup.dynamic.dto.*;
import io.swagger.annotations.*;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : water
 * @Date : 2016年9月11日
 * @Desc : TODO
 * @version: V1.0
 */

@RestController
@RequestMapping(value = "/api/")
@Api(value = "任务调度", description = "任务调度api")
public class ScheduleController {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

	@Resource
	private ScheduleService scheduleService;
	/**
	 * 添加job
	 * 
	 * @param scheduleDTO
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "添加调度任务",  notes = "给某个场景添加调度任务<br><br><b>@author water</b>" )
	public ResponseEntity<ResultDTO> addSchedule(@ApiParam(value = "调度DTO<br><br> 必填字段：scenarioId(即jobName) ，jobGroup ，triggerType（触发器类型SimpleTrigger 还是 CronTrigger）<br><br> ") @RequestBody ScheduleDTO scheduleDTO)
			throws ScheduleException {

		if (logger.isDebugEnabled()) {
			logger.debug("add Job  with triggerTiype: {} and ScenarioId : {} and jobGroup : {}",
					scheduleDTO.getTriggerType(), scheduleDTO.getScenarioId(), scheduleDTO.getJobGroup());
		}
		
		return new ResponseEntity<ResultDTO>(scheduleService.addJob(scheduleDTO), HttpStatus.OK);
	}

	/**
	 * delete job
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 * @throws ScheduleException
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation( value = "删除job",  notes = "删除job<br>@author water")
	public ResponseEntity<ResultDTO> deleteJob(
			@ApiParam(value = "job名称", required = true) @RequestParam(value = "jobName") String jobName,
			@ApiParam(value = "job组", required = true) @RequestParam(value = "jobGroup") String jobGroup)
			throws ScheduleException {

		if (logger.isDebugEnabled()) {
			logger.debug("delete Job  with  jobName : {} and jobGroup : {}", jobName, jobGroup);
		}
		Scheduler scheduler = null;
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<ResultDTO>(scheduleService.deleteJob(scheduler, jobKey), HttpStatus.OK);
	}

	/**
	 * 立即运行，只会运行一次，方便测试时用
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 * @throws ScheduleException
	 */
	@RequestMapping(value = "/runOnce", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "立即运行，只会运行一次，方便测试时用",  notes = "立即运行，只会运行一次，方便测试时用<br>@author water")
	public ResponseEntity<?> runOnceJob(
			@ApiParam(value = "job名称", required = true) @RequestParam(value = "jobName", required = true) String jobName,
			@ApiParam(value = "job组", required = true) @RequestParam(value = "jobGroup", required = true) String jobGroup)
			throws ScheduleException {

		if (logger.isDebugEnabled()) {
			logger.debug("runOnce Job  with  jobName : {} and jobGroup : {}", jobName, jobGroup);
		}
		Scheduler scheduler = null;
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scheduleService.runOnce(scheduler, jobKey);
		return new ResponseEntity<Object>("success", HttpStatus.OK);
	}

	/**
	 * 获取计划中的任务列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getJobListInPlan", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "获取计划中的任务列表", response = Void.class, notes = "获取计划中的任务列表<br>@author water")
	public ResponseEntity<?> getJobListInPlan() throws Exception {

		Scheduler scheduler = null;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getJobListInPlan Job  with  jobName : {} and jobGroup : {}");
		}
		return new ResponseEntity<List<ScheduleJob>>(scheduleService.jobInPlan(scheduler), HttpStatus.OK);
	}

	/**
	 * 根据状态获取job列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getJobListByStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "根据状态获取job列表", response = Void.class, notes = "根据状态获取job列表<br>@author water")
	public ResponseEntity<?> getJobListByStatus(
			@ApiParam(value = "job运行状态（NONE, NORMAL, PAUSED, COMPLETE, ERROR, BLOCKED）", required = true) @RequestParam(value = "jobStatus", required = true) String jobStatus)
			throws Exception {

		Scheduler scheduler = null;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getJobListInPlan Job  with  jobName : {} and jobGroup : {}");
		}
		return new ResponseEntity<List<ScheduleDTO>>(scheduleService.getJobListByStatus(scheduler,jobStatus ), HttpStatus.OK);
	}

	/**
	 * 获取运行中的任务列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getJobListInRunning", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "获取运行中的任务列表", response = Void.class, notes = "获取运行中的任务列表<br>@author water")
	public ResponseEntity<?> getJobListInRunning() throws Exception {

		Scheduler scheduler = null;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getJobListInPlan Job  with  jobName : {} and jobGroup : {}");
		}
		return new ResponseEntity<List<ScheduleJob>>(scheduleService.jobInRunning(scheduler), HttpStatus.OK);
	}

	/**
	 * 暂停定时任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 * @throws ScheduleException
	 */
	@RequestMapping(value = "/pauseJob", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "暂停定时任务", response = Void.class, notes = "暂停定时任务<br>@author water")
	public ResponseEntity<?> pauseJob(
			@ApiParam(value = "job名称", required = true) @RequestParam(value = "jobName", required = true) String jobName,
			@ApiParam(value = "job组", required = true) @RequestParam(value = "jobGroup", required = true) String jobGroup)
			throws ScheduleException {

		if (logger.isDebugEnabled()) {
			logger.debug("runOnce Job  with  jobName : {} and jobGroup : {}", jobName, jobGroup);
		}
		Scheduler scheduler = null;
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scheduleService.pauseJob(scheduler, jobKey);
		return new ResponseEntity<Object>("success", HttpStatus.OK);
	}

	/**
	 * 恢复定时任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 * @throws ScheduleException
	 */
	@RequestMapping(value = "/resumeJob", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "恢复定时任务", response = Void.class, notes = "恢复定时任务<br>@author water")
	public ResponseEntity<?> resumeJob(
			@ApiParam(value = "job名称", required = true) @RequestParam(value = "jobName", required = true) String jobName,
			@ApiParam(value = "job组", required = true) @RequestParam(value = "jobGroup", required = true) String jobGroup)
			throws ScheduleException {

		if (logger.isDebugEnabled()) {
			logger.debug("runOnce Job  with  jobName : {} and jobGroup : {}", jobName, jobGroup);
		}
		Scheduler scheduler = null;
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scheduleService.resumeJob(scheduler, jobKey);
		return new ResponseEntity<Object>("success", HttpStatus.OK);
	}

	/**
	 * @param scheduleDTO
	 * @return
	 * @throws ScheduleException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "更新job", response = Void.class, notes = "更新job<br>@author water")
	public ResponseEntity<?> update(@ApiParam(value = "调度DTO") @RequestBody ScheduleDTO scheduleDTO)
			throws ScheduleException {

		scheduleService.updateJob(scheduleDTO);
		return new ResponseEntity<Object>("success", HttpStatus.OK);
	}

}