package io.jiangbyte.app.modules.system.log.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.system.log.entity.SysLog;
import io.jiangbyte.app.modules.system.log.param.SysLogAddParam;
import io.jiangbyte.app.modules.system.log.param.SysLogEditParam;
import io.jiangbyte.app.modules.system.log.param.SysLogIdParam;
import io.jiangbyte.app.modules.system.log.param.SysLogPageParam;
import io.jiangbyte.app.modules.system.log.mapper.SysLogMapper;
import io.jiangbyte.app.modules.system.log.service.SysLogService;
import io.jiangbyte.framework.enums.ISortOrderEnum;
import io.jiangbyte.framework.exception.BusinessException;
import io.jiangbyte.framework.pojo.BasePageRequest;
import io.jiangbyte.framework.result.ResultCode;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-06-23
* @description 系统活动日志记录表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public Page<SysLog> page(SysLogPageParam sysLogPageParam) {
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<SysLog>().checkSqlInjection();
        if (ObjectUtil.isAllNotEmpty(sysLogPageParam.getSortField(), sysLogPageParam.getSortOrder()) && ISortOrderEnum.isValid(sysLogPageParam.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    sysLogPageParam.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(sysLogPageParam.getSortField()));
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(sysLogPageParam.getCurrent()).orElse(1),
                        Optional.ofNullable(sysLogPageParam.getSize()).orElse(10)
                ),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SysLogAddParam sysLogAddParam) {
        SysLog bean = BeanUtil.toBean(sysLogAddParam, SysLog.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SysLogEditParam sysLogEditParam) {
        if (!this.exists(new LambdaQueryWrapper<SysLog>().eq(SysLog::getId, sysLogEditParam.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        SysLog bean = BeanUtil.toBean(sysLogEditParam, SysLog.class);
        BeanUtil.copyProperties(sysLogEditParam, bean);
        this.updateById(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SysLogIdParam> sysLogIdParamList) {
        if (ObjectUtil.isEmpty(sysLogIdParamList)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        this.removeByIds(CollStreamUtil.toList(sysLogIdParamList, SysLogIdParam::getId));
    }

    @Override
    public SysLog detail(SysLogIdParam sysLogIdParam) {
        SysLog sysLog = this.getById(sysLogIdParam.getId());
        if (ObjectUtil.isEmpty(sysLog)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return sysLog;
    }

    @Override
    public List<SysLog> latest(int n) {
        return this.list(new QueryWrapper<SysLog>()
            .lambda()
            .orderByDesc(SysLog::getId)
            .last("limit " + n));
    }

    @Override
    public List<SysLog> topN(int n) {
        return this.list(new QueryWrapper<SysLog>()
            .lambda()
            .orderByDesc(SysLog::getId)
            .last("limit " + n));
    }

}