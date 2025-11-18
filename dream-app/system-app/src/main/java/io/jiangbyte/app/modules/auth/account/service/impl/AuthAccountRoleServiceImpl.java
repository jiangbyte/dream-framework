package io.jiangbyte.app.modules.auth.account.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.auth.account.entity.AuthAccountRole;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountRoleAddParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountRoleEditParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountRolePageParam;
import io.jiangbyte.app.modules.auth.account.mapper.AuthAccountRoleMapper;
import io.jiangbyte.app.modules.auth.account.service.AuthAccountRoleService;
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
* @description 账户角色关联表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthAccountRoleServiceImpl extends ServiceImpl<AuthAccountRoleMapper, AuthAccountRole> implements AuthAccountRoleService {

    @Override
    public Page<AuthAccountRole> page(AuthAccountRolePageParam req) {
        QueryWrapper<AuthAccountRole> queryWrapper = new QueryWrapper<AuthAccountRole>().checkSqlInjection();
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
    public void add(AuthAccountRoleAddParam req) {
        AuthAccountRole bean = BeanUtil.toBean(req, AuthAccountRole.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(AuthAccountRoleEditParam req) {
        if (!this.exists(new LambdaQueryWrapper<AuthAccountRole>().eq(AuthAccountRole::getId, req.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        AuthAccountRole bean = BeanUtil.toBean(req, AuthAccountRole.class);
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
    public AuthAccountRole detail(String id) {
        AuthAccountRole authAccountRole = this.getById(id);
        if (ObjectUtil.isEmpty(authAccountRole)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return authAccountRole;
    }

    @Override
    public List<AuthAccountRole> latest(int n) {
        return this.list(new QueryWrapper<AuthAccountRole>()
            .lambda()
            .orderByDesc(AuthAccountRole::getId)
            .last("limit " + n));
    }

    @Override
    public List<AuthAccountRole> topN(int n) {
        return this.list(new QueryWrapper<AuthAccountRole>()
            .lambda()
            .orderByDesc(AuthAccountRole::getId)
            .last("limit " + n));
    }

}