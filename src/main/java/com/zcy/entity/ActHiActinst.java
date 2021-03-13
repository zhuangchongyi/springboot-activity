package com.zcy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Title 历史活动任务实体类
 * @Description 
 * @author zhanghui
 * @date 2020年07月21日 9:59
 * @version V1.0
 * @see 
 * @since V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActHiActinst {

	private String id;

	private String procDefId;

	private String procInstId;

	private String executionId;

	private String actId;

	private String taskId;

	private String callProcInstId;

	private String actName;

	private String actType;

	private String assignee;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private Long duration;

	private String tenantId;
}
