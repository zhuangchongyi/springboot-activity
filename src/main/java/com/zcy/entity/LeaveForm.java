package com.zcy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title 请假表单
 * @Description 
 * @author zhanghui
 * @date 2020年07月20日 9:49
 * @version V1.0
 * @see 
 * @since V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("leave_form")
public class LeaveForm {

	@TableId(value="id",type=IdType.UUID)
	private String id;
	/** 发起人Id */
	@TableField(value = "proposer_id")
	private String proposerId;
	/** 实例ID */
	@TableField(value = "proc_instan_id")
	private String procInstanId;
	/** 代理人ID */
	@TableField(value = "agent_id")
	private String agentId;
	/** 标题 */
	private String title;
	/** 备注 */
	@TableField(value = "leave_content")
	private String leaveContent;
	/** 回复内容 */
	private String reply;
	/** 状态 */
	private Integer status;

}
