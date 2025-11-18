package io.jiangbyte.app.modules.auth.reset.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.auth.reset.entity.AuthPasswordReset;
import io.jiangbyte.app.modules.auth.reset.param.AuthPasswordResetAddParam;
import io.jiangbyte.app.modules.auth.reset.param.AuthPasswordResetEditParam;
import io.jiangbyte.app.modules.auth.reset.param.AuthPasswordResetPageParam;
import io.jiangbyte.app.modules.auth.reset.mapper.AuthPasswordResetMapper;
import io.jiangbyte.app.modules.auth.reset.service.AuthPasswordResetService;
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
* @description 密码重置表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthPasswordResetServiceImpl extends ServiceImpl<AuthPasswordResetMapper, AuthPasswordReset> implements AuthPasswordResetService {

    @Override
    public Page<AuthPasswordReset> page(AuthPasswordResetPageParam req) {
        QueryWrapper<AuthPasswordReset> queryWrapper = new QueryWrapper<AuthPasswordReset>().checkSqlInjection();
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
    public void add(AuthPasswordResetAddParam req) {
        AuthPasswordReset bean = BeanUtil.toBean(req, AuthPasswordReset.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(AuthPasswordResetEditParam req) {
        if (!this.exists(new LambdaQueryWrapper<AuthPasswordReset>().eq(AuthPasswordReset::getId, req.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        AuthPasswordReset bean = BeanUtil.toBean(req, AuthPasswordReset.class);
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
    public AuthPasswordReset detail(String id) {
        AuthPasswordReset authPasswordReset = this.getById(id);
        if (ObjectUtil.isEmpty(authPasswordReset)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return authPasswordReset;
    }

    @Override
    public List<AuthPasswordReset> latest(int n) {
        return this.list(new QueryWrapper<AuthPasswordReset>()
            .lambda()
            .orderByDesc(AuthPasswordReset::getId)
            .last("limit " + n));
    }

    @Override
    public List<AuthPasswordReset> topN(int n) {
        return this.list(new QueryWrapper<AuthPasswordReset>()
            .lambda()
            .orderByDesc(AuthPasswordReset::getId)
            .last("limit " + n));
    }

}