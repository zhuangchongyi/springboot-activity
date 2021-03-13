package com.zcy.service;


import com.zcy.common.datasource.DataSource;
import com.zcy.common.datasource.DataSourceType;
import com.zcy.entity.ActHiActinst;
import com.zcy.mapper.ActHiActinstMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActHiActInstServiceImpl implements IActHiActInstService {

    @Autowired(required = false)
    ActHiActinstMapper actHiActinstMapper;

    /**
     * @描述: 通过 实例Id 删除与该实例历史活动相关信息
     * @params:
     * @return:
     * @date: 2018/9/23 20:49
     */
    @DataSource(DataSourceType.SALVE)
    @Override
    public int deleteByProcInstId(String[] procInstIds) {
        return actHiActinstMapper.deleteByProcInstId(procInstIds);
    }

    /**
     * @描述: 通过实例Id 获取该活动信息
     * @params:
     * @return:
     * @date: 2018/9/23 20:50
     */
    @DataSource(DataSourceType.SALVE)
    @Override
    public List<ActHiActinst> selectByByProcInstId(String procInstId) {
        return actHiActinstMapper.selectByByProcInstId(procInstId);
    }
}
