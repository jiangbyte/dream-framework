package io.jiangbyte.app.modules.user.stats.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.user.stats.entity.UserStats;
import io.jiangbyte.app.modules.user.stats.param.UserStatsAddParam;
import io.jiangbyte.app.modules.user.stats.param.UserStatsEditParam;
import io.jiangbyte.app.modules.user.stats.param.UserStatsPageParam;
import io.jiangbyte.app.modules.user.stats.mapper.UserStatsMapper;
import io.jiangbyte.app.modules.user.stats.service.UserStatsService;
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
* @description 用户统计信息表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserStatsServiceImpl extends ServiceImpl<UserStatsMapper, UserStats> implements UserStatsService {

    @Override
    public Page<UserStats> page(UserStatsPageParam req) {
        QueryWrapper<UserStats> queryWrapper = new QueryWrapper<UserStats>().checkSqlInjection();
        if (ObjectUtil.isAllNotEmpty(req.getSortField(), req.getSortOrder()) && ISortOrderEnum.isValid(req.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    req.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(req.getSortField()));
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(req.getCurrent()).orElse(1),
                        Optional.ofNullable(req.getPageSize()).orElse(10)),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserStatsAddParam req) {
        UserStats bean = BeanUtil.toBean(req, UserStats.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(UserStatsEditParam req) {
        if (!this.exists(new LambdaQueryWrapper<UserStats>().eq(UserStats::getId, req.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        UserStats bean = BeanUtil.toBean(req, UserStats.class);
        BeanUtil.copyProperties(req, bean);
        this.updateById(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<String> ids) {
        if (ObjectUtil.isEmpty(ids)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        this.removeByIds(ids);
    }

    @Override
    public UserStats detail(String id) {
        UserStats userStats = this.getById(id);
        if (ObjectUtil.isEmpty(userStats)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return userStats;
    }

    @Override
    public List<UserStats> latest(int n) {
        return this.list(new QueryWrapper<UserStats>()
            .lambda()
            .orderByDesc(UserStats::getId)
            .last("limit " + n));
    }

    @Override
    public List<UserStats> topN(int n) {
        return this.list(new QueryWrapper<UserStats>()
            .lambda()
            .orderByDesc(UserStats::getId)
            .last("limit " + n));
    }

}