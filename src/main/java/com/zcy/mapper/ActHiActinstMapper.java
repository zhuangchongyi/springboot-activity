package com.zcy.mapper;


import com.zcy.entity.ActHiActinst;

import java.util.List;

public interface ActHiActinstMapper {

    /** 删除活动历史信息 */
    int deleteByProcInstId(String[] procInstId);

    /** 根据进程实例id获取当前 实例的活动信息 */
    List<ActHiActinst> selectByByProcInstId(String procInstId);

}