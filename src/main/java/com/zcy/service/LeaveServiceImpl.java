package com.zcy.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.entity.LeaveForm;
import com.zcy.mapper.LeaveMapper;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghui
 * @version V1.0
 * @Title
 * @Description
 * @date 2020年07月20日 14:01
 * @see
 * @since V1.0
 */
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, LeaveForm> implements LeaveService {

    //请假工作流的进程ID
    private final static String ProcessInstanceByKey = "leaveProce_ID";

    @Autowired(required = false)
    IdentityService identityService;
    /**
     * 开启任务相关的Service对象
     */
    @Autowired(required = false)
    RuntimeService runtimeService;
    @Autowired(required = false)
    TaskService taskService;


    /**
     * <p> 描述 : 提交表单
     *
     * @author : blackcat
     * @date : 2020/7/21 15:19
     * <p>
     * 这个的流程图是一开始为了学习从别的博客复制过来的，设计的有点不太合理，为了快速写完测试，并没有进行修改。理解流程图的流程即可。
     * 提交，审核等方法，完全可以合并为一个方法或看个人风格修改，这里为了方便新手了解，每一步都拆开成独立方法。
     */
    @Override
    public String saveApply(String uid) {
        LeaveForm leaveForm = new LeaveForm();
        leaveForm.setProposerId(uid);
        leaveForm.setLeaveContent("请假事由");
        leaveForm.setTitle("请假申请");
        /** 代理人ID 下一个审核人 */
        leaveForm.setAgentId("2");
        /** 1:预约中 2:同意请假 3:拒绝请假 4:放弃请假 */
        leaveForm.setStatus(1);

        // 1.设置流程启动人
        identityService.setAuthenticatedUserId(uid);
        // 2.预约表单信息持久化
        baseMapper.insert(leaveForm);
        // 3.启动申请流程,设置变量到下一个节点
        Map<String, Object> map = new HashMap<>(2);
        map.put("agent", leaveForm.getProposerId());// 流程的代理人
        map.put("formId", leaveForm.getId());// 任务表的表单id字段
        map.put("flag", "true");// 排他网管的表达式字符窜 flag=true
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ProcessInstanceByKey, leaveForm.getId(), map);
        leaveForm.setProcInstanId(processInstance.getId());
        // 4.修改实例ID
        baseMapper.updateById(leaveForm);
        // 通过proceId获取任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        // 5.完成填写任务
        taskService.complete(task.getId(), map);
        return leaveForm.getProposerId();
    }

    @Override
    public void submitApply(String proceId) {
        // 部门经理审批【请假申请】
        Task task2 = taskService.createTaskQuery().processInstanceId(proceId).singleResult();
        // 指定下一个 任务代理人 下一个还是自己
        Map<String, Object> map2 = new HashMap<>(2);
        map2.put("agent", "2");// 流程的代理人
        map2.put("formId", task2.getFormKey());// 任务表的表单id字段
        map2.put("flag", "true");// 排他网管的表达式字符窜 flag=true
        taskService.complete(task2.getId(), map2);

        // 部门经理审批 同意
        Task task3 = taskService.createTaskQuery().processInstanceId(proceId).singleResult();
        // 指定下一个 任务代理人 送到boss
        Map<String, Object> map3 = new HashMap<>(3);
        map3.put("agent", "3");// 流程的代理人
        map3.put("formId", task3.getFormKey());// 任务表的表单id字段
        map3.put("flag", "true");// 排他网管的表达式字符窜 flag=true
        taskService.complete(task3.getId(), map2);

        //修改表单状态
        LeaveForm leaveForm = new LeaveForm();
        leaveForm.setId(task2.getFormKey());
        /** 代理人ID 下一个审核人 */
        leaveForm.setAgentId("3");
        /** 1:预约中 2:同意请假 3:拒绝请假 4:放弃请假 */
        leaveForm.setStatus(2);
        baseMapper.updateById(leaveForm);
    }

    @Override
    public void bossApply(String procInstId) {
        // boss同意
        Task task = taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
        Map<String, Object> map = new HashMap<>(2);
        map.put("agent", "3");// 流程的代理人
        map.put("formId", task.getFormKey());// 任务表的表单id字段
        map.put("flag", "true");// 排他网管的表达式字符窜 flag=true
        taskService.complete(task.getId(), map);
        //修改表单状态
        LeaveForm leaveForm = new LeaveForm();
        leaveForm.setId(task.getFormKey());
        /** 代理人ID 回到自己列表 */
        leaveForm.setAgentId("1");
        baseMapper.updateById(leaveForm);
    }

    @Override
    public void giveUp(String proceId) {
        //放弃申请
        Task task = taskService.createTaskQuery().processInstanceId(proceId).singleResult();
        Map<String, Object> map = new HashMap<>(2);
        map.put("agent", task.getAssignee());// 流程的代理人
        map.put("formId", task.getFormKey());// 任务表的表单id字段
        map.put("flag", "false");// 排他网管的表达式字符窜 flag=true
        taskService.complete(task.getId(), map);

        //结束任务
        Task task2 = taskService.createTaskQuery().processInstanceId(proceId).singleResult();
        taskService.complete(task2.getId());

        //4：代表放弃请假
        LeaveForm leave = baseMapper.selectById(task.getFormKey());
        leave.setStatus(4);
        baseMapper.updateById(leave);
    }
}
