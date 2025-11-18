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
import io.jiangbyte.app.modules.auth.account.param.AuthAccountRoleIdParam;
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
    public Page<AuthAccountRole> page(AuthAccountRolePageParam authAccountRolePageParam) {
        QueryWrapper<AuthAccountRole> queryWrapper = new QueryWrapper<AuthAccountRole>().checkSqlInjection();
        if (ObjectUtil.isAllNotEmpty(authAccountRolePageParam.getSortField(), authAccountRolePageParam.getSortOrder()) && ISortOrderEnum.isValid(authAccountRolePageParam.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    authAccountRolePageParam.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(authAccountRolePageParam.getSortField()));
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(authAccountRolePageParam.getCurrent()).orElse(1),
                        Optional.ofNullable(authAccountRolePageParam.getSize()).orElse(10)
                ),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(AuthAccountRoleAddParam authAccountRoleAddParam) {
        AuthAccountRole bean = BeanUtil.toBean(authAccountRoleAddParam, AuthAccountRole.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(AuthAccountRoleEditParam authAccountRoleEditParam) {
        if (!this.exists(new LambdaQueryWrapper<AuthAccountRole>().eq(AuthAccountRole::getId, authAccountRoleEditParam.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        AuthAccountRole bean = BeanUtil.toBean(authAccountRoleEditParam, AuthAccountRole.class);
        BeanUtil.copyProperties(authAccountRoleEditParam, bean);
        this.updateById(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<AuthAccountRoleIdParam> authAccountRoleIdParamList) {
        if (ObjectUtil.isEmpty(authAccountRoleIdParamList)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        this.removeByIds(CollStreamUtil.toList(authAccountRoleIdParamList, AuthAccountRoleIdParam::getId));
    }

    @Override
    public AuthAccountRole detail(AuthAccountRoleIdParam authAccountRoleIdParam) {
        AuthAccountRole authAccountRole = this.getById(authAccountRoleIdParam.getId());
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