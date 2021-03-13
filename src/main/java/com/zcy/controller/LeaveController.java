package com.zcy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zcy.entity.ActHiActinst;
import com.zcy.entity.LeaveForm;
import com.zcy.entity.User;
import com.zcy.service.IActHiActInstService;
import com.zcy.service.LeaveService;
import com.zcy.service.UserService;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhanghui
 * @version V1.0
 * @Title 请假
 * @Description
 * @date 2020年07月20日 14:18
 * @see
 * @since V1.0
 */
@Controller
@RequestMapping("leave")
public class LeaveController {

    @Autowired(required = false)
    private LeaveService leaveService;
    @Autowired
    IActHiActInstService iActHiActInstService;
    @Autowired
    private UserService userService;

    /**
     * 跳转请假管理页面
     */
    @RequestMapping("/toLeave")
    public String toLeave(String loginName, ModelMap map) {
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.eq("login_name", loginName);
        User user = userService.getOne(userWrapper);
        map.put("user", user);
        return "/leave/leave";
    }

    /**
     * 请假表单列表数据查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public List<LeaveForm> list(String uid) {
        QueryWrapper<LeaveForm> queryWrapper = new QueryWrapper();
        queryWrapper.eq("proposer_id", uid).or().eq("agent_id", uid);
        List<LeaveForm> list = leaveService.list(queryWrapper);
        return list;
    }

    /**
     * 添加请假申请表单信息
     */
    @RequestMapping("/addApply")
    @ResponseBody
    public String addApply(String uid) {
        return leaveService.saveApply(uid);
    }

    /**
     * 经理 提交申请
     */
    @RequestMapping("/submitApply")
    public void submitApply(String procInstId) {
        leaveService.submitApply(procInstId);
    }

    /**
     * 经理 提交申请
     */
    @RequestMapping("/bossApply")
    public void bossApply(String procInstId) {
        leaveService.bossApply(procInstId);
    }

    /**
     * 放弃申请，结束流程
     */
    @RequestMapping("/giveUp")
    public void giveUp(String procInstId) {
        leaveService.giveUp(procInstId);
    }

    /**
     * 通过实例Id 获取该活动信息 查看过程
     */
    @RequestMapping("/HiProcActiList/{procInstId}")
    @ResponseBody
    public List<ActHiActinst> ActiList(@PathVariable("procInstId") String procInstId) {
        return iActHiActInstService.selectByByProcInstId(procInstId);
    }

    public static void main(String[] args) {
        // 部署请假流程
        RepositoryService rs = ProcessEngines.getDefaultProcessEngine().getRepositoryService();
        Deployment deploy = rs.createDeployment().addClasspathResource("processes/leave.bpmn").deploy();
        rs.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
    }
}
