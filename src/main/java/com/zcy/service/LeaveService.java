package com.zcy.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.entity.LeaveForm;

/**
 * @Title 
 * @Description 
 * @author zhanghui
 * @date 2020年07月20日 14:00
 * @version V1.0
 * @see 
 * @since V1.0
 */
public interface LeaveService extends IService<LeaveForm> {

	/** 填写请假表单信息 */
	String saveApply(String uid);

	/** 经理 提交申请 */
	void submitApply(String procInstId);

	/** boss提交申请 */
	void bossApply(String procInstId);

	/** 放弃申请 */
	void giveUp(String procInstId);


}
