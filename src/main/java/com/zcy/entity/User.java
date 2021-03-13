package com.zcy.entity;

import lombok.Data;

/**
 * @Title 用户
 * @Description 
 * @author zhanghui
 * @date 2020年07月20日 9:45
 * @version V1.0
 * @see 
 * @since V1.0
 */
@Data
public class User {

	/** 主键 */
	private String uid;
	/** 密码 */
	private String pwd;
	/** 登录名称 */
	private String login_name;
	/** 用户名称 */
	private String name;
	/** 权限id 1:员工 2:经理 3:boss */
	private String role_id;
}
