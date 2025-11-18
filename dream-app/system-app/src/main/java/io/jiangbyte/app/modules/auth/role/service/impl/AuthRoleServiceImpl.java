package io.jiangbyte.app.modules.auth.role.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.auth.role.entity.AuthRole;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleAddParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleEditParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleIdParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRolePageParam;
import io.jiangbyte.app.modules.auth.role.mapper.AuthRoleMapper;
import io.jiangbyte.app.modules.auth.role.service.AuthRoleService;
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
* @description 角色表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRole> implements AuthRoleService {

    @Override
    public Page<AuthRole> page(AuthRolePageParam authRolePageParam) {
        QueryWrapper<AuthRole> queryWrapper = new QueryWrapper<AuthRole>().checkSqlInjection();
        // 关键字
        if (ObjectUtil.isNotEmpty(authRolePageParam.getKeyword())) {
            queryWrapper.lambda().like(AuthRole::getName, authRolePageParam.getKeyword());
        }
        if (ObjectUtil.isAllNotEmpty(authRolePageParam.getSortField(), authRolePageParam.getSortOrder()) && ISortOrderEnum.isValid(authRolePageParam.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    authRolePageParam.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(authRolePageParam.getSortField()));
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(authRolePageParam.getCurrent()).orElse(1),
                        Optional.ofNullable(authRolePageParam.getSize()).orElse(10)
                ),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(AuthRoleAddParam authRoleAddParam) {
        AuthRole bean = BeanUtil.toBean(authRoleAddParam, AuthRole.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(AuthRoleEditParam authRoleEditParam) {
        if (!this.exists(new LambdaQueryWrapper<AuthRole>().eq(AuthRole::getId, authRoleEditParam.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        AuthRole bean = BeanUtil.toBean(authRoleEditParam, AuthRole.class);
        BeanUtil.copyProperties(authRoleEditParam, bean);
        this.updateById(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<AuthRoleIdParam> authRoleIdParamList) {
        if (ObjectUtil.isEmpty(authRoleIdParamList)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        this.removeByIds(CollStreamUtil.toList(authRoleIdParamList, AuthRoleIdParam::getId));
    }

    @Override
    public AuthRole detail(AuthRoleIdParam authRoleIdParam) {
        AuthRole authRole = this.getById(authRoleIdParam.getId());
        if (ObjectUtil.isEmpty(authRole)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return authRole;
    }

    @Override
    public List<AuthRole> latest(int n) {
        return this.list(new QueryWrapper<AuthRole>()
            .lambda()
            .orderByDesc(AuthRole::getId)
            .last("limit " + n));
    }

    @Override
    public List<AuthRole> topN(int n) {
        return this.list(new QueryWrapper<AuthRole>()
            .lambda()
            .orderByDesc(AuthRole::getId)
            .last("limit " + n));
    }

}