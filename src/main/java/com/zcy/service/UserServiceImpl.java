package com.zcy.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.entity.User;
import com.zcy.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @Title 
 * @Description 
 * @author zhanghui
 * @date 2020年07月20日 14:01
 * @version V1.0
 * @see 
 * @since V1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
}
