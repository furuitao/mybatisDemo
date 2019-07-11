package com.finup.dynamic.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Map;

/**
 * @Author : water
 * @Date : 2016年9月11日
 * @Desc : 任务调度网络传输对象
 * @version: V1.0
 */
public class ScheduleDTO {

	@ApiModelProperty(position = 1 ,value = "触发器类型（SimpleTrigger 还是 CronTrigger）", required = true)
	private String triggerType;

//	@Size(min = 1, max = 255)
	@ApiModelProperty(position = 2 , value = "场景id（job名称）", required = true)
	private String scenarioId;

//	@Size(min = 1, max = 255 )
	@ApiModelProperty(position = 3 ,value = "job分组", required = true)
	private String jobGroup;

	@ApiModelProperty(position = 4 ,value = "是否异步")
	private Boolean isSync;

	@ApiModelProperty(position = 5 ,value = "修改时间")
	private Date modifyTime;

	@ApiModelProperty(position = 6 ,value = "任务属性或者配置")
	private Map<String, Object> jobDataMap;
	
//	@Size(min = 1, max = 255)
	@ApiModelProperty(position = 7 ,value = "job状态" )
	private String status;
	
//	@Size(min = 1, max = 255)
	@ApiModelProperty(position = 8 ,value = "job描述信息")
	private String description;

	/*
	 * cron trigger
	 * 
	 */

	@ApiModelProperty(position = 9 ,value = "任务运行时间表达式（CronTrigger类型触发器的 属性）")
	private String cronExpression;

	/*
	 * simple trigger
	 * 
	 */

	@ApiModelProperty(position = 10 ,value = "创建时间（SimpleTrigger类型触发器的 属性）")
	private Date startTime;

	@ApiModelProperty(position = 11 ,value = "结束时间（SimpleTrigger类型触发器的 属性）")
	private Date endTime;

	@ApiModelProperty(position = 12 ,value = "重复次数（SimpleTrigger类型触发器的 属性）")
	private int repeatCount;

	@ApiModelProperty(position = 13 ,value = "重复间隔，单位秒（SimpleTrigger类型触发器的 属性）")
	private int repeatInterval;

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public String getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public Boolean getSync() {
		return isSync;
	}

	public void setSync(Boolean sync) {
		isSync = sync;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Map<String, Object> getJobDataMap() {
		return jobDataMap;
	}

	public void setJobDataMap(Map<String, Object> jobDataMap) {
		this.jobDataMap = jobDataMap;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public int getRepeatInterval() {
		return repeatInterval;
	}

	public void setRepeatInterval(int repeatInterval) {
		this.repeatInterval = repeatInterval;
	}
}
