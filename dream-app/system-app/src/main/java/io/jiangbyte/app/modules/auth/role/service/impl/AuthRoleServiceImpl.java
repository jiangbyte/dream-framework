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
import io.jiangbyte.app.modules.auth.role.param.AuthRolePageParam;
import io.jiangbyte.app.modules.auth.role.mapper.AuthRoleMapper;
import io.jiangbyte.app.modules.auth.role.service.AuthRoleService;
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
* @description 角色表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRole> implements AuthRoleService {

    @Override
    public Page<AuthRole> page(AuthRolePageParam req) {
        QueryWrapper<AuthRole> queryWrapper = new QueryWrapper<AuthRole>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(req.getKeyword())) {
            queryWrapper.lambda().like(AuthRole::getName, req.getKeyword());
        }
        SortUtils.handleSort(AuthRole.class, queryWrapper, req.getSortField(), req.getSortOrder());
        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(req.getCurrent()).orElse(1),
                        Optional.ofNullable(req.getPageSize()).orElse(10)),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(AuthRoleAddParam req) {
        AuthRole bean = BeanUtil.toBean(req, AuthRole.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(AuthRoleEditParam req) {
        if (!this.exists(new LambdaQueryWrapper<AuthRole>().eq(AuthRole::getId, req.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        AuthRole bean = BeanUtil.toBean(req, AuthRole.class);
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
    public AuthRole detail(String id) {
        AuthRole authRole = this.getById(id);
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