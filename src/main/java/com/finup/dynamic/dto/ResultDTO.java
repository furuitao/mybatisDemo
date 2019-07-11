package com.finup.dynamic.dto;

import io.swagger.annotations.ApiModelProperty;


/**
 * @Author : water
 * @Date : 2016年9月11日
 * @Desc : 运行结果网络传输对象
 * @version: V1.0
 */

public class ResultDTO {

	@ApiModelProperty(value = "运行结果状态码", required = true)
	private int resultCode;

	@ApiModelProperty(value = "运行结果信息", required = false)
	private String resultMessage;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
}
