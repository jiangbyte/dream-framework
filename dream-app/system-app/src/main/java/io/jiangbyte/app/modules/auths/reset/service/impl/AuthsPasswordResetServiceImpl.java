package io.jiangbyte.app.modules.auths.reset.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.auths.reset.entity.AuthsPasswordReset;
import io.jiangbyte.app.modules.auths.reset.dto.AuthsPasswordResetDto;
import io.jiangbyte.app.modules.auths.reset.dto.AuthsPasswordResetPageQuery;
import io.jiangbyte.app.modules.auths.reset.mapper.AuthsPasswordResetMapper;
import io.jiangbyte.app.modules.auths.reset.service.AuthsPasswordResetService;
import io.jiangbyte.framework.utils.SortUtils;
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
public class AuthsPasswordResetServiceImpl extends ServiceImpl<AuthsPasswordResetMapper, AuthsPasswordReset> implements AuthsPasswordResetService {

    @Override
    public Page<AuthsPasswordReset> page(AuthsPasswordResetPageQuery req) {
        QueryWrapper<AuthsPasswordReset> queryWrapper = new QueryWrapper<AuthsPasswordReset>().checkSqlInjection();
        SortUtils.handleSort(AuthsPasswordReset.class, queryWrapper, req.getSortField(), req.getSortOrder());
        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(req.getCurrent()).orElse(1),
                        Optional.ofNullable(req.getPageSize()).orElse(10)),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(AuthsPasswordResetDto req) {
        AuthsPasswordReset bean = BeanUtil.toBean(req, AuthsPasswordReset.class);
        bean.setId(null);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(AuthsPasswordResetDto req) {
        if (!this.exists(new LambdaQueryWrapper<AuthsPasswordReset>().eq(AuthsPasswordReset::getId, req.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        AuthsPasswordReset bean = BeanUtil.toBean(req, AuthsPasswordReset.class);
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
    public AuthsPasswordReset detail(String id) {
        AuthsPasswordReset authsPasswordReset = this.getById(id);
        if (ObjectUtil.isEmpty(authsPasswordReset)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return authsPasswordReset;
    }

    @Override
    public List<AuthsPasswordReset> latest(int n) {
        return this.list(new QueryWrapper<AuthsPasswordReset>()
            .lambda()
            .orderByDesc(AuthsPasswordReset::getId)
            .last("limit " + n));
    }

    @Override
    public List<AuthsPasswordReset> topN(int n) {
        return this.list(new QueryWrapper<AuthsPasswordReset>()
            .lambda()
            .orderByDesc(AuthsPasswordReset::getId)
            .last("limit " + n));
    }

}