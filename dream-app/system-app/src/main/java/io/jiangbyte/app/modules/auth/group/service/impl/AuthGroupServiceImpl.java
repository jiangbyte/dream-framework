package io.jiangbyte.app.modules.auth.group.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.auth.group.entity.AuthGroup;
import io.jiangbyte.app.modules.auth.group.param.AuthGroupAddParam;
import io.jiangbyte.app.modules.auth.group.param.AuthGroupEditParam;
import io.jiangbyte.app.modules.auth.group.param.AuthGroupPageParam;
import io.jiangbyte.app.modules.auth.group.mapper.AuthGroupMapper;
import io.jiangbyte.app.modules.auth.group.service.AuthGroupService;
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
* @description 用户组表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthGroupServiceImpl extends ServiceImpl<AuthGroupMapper, AuthGroup> implements AuthGroupService {

    @Override
    public Page<AuthGroup> page(AuthGroupPageParam req) {
        QueryWrapper<AuthGroup> queryWrapper = new QueryWrapper<AuthGroup>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(req.getKeyword())) {
            queryWrapper.lambda().like(AuthGroup::getName, req.getKeyword());
        }
        SortUtils.handleSort(AuthGroup.class, queryWrapper, req.getSortField(), req.getSortOrder());
        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(req.getCurrent()).orElse(1),
                        Optional.ofNullable(req.getPageSize()).orElse(10)),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(AuthGroupAddParam req) {
        AuthGroup bean = BeanUtil.toBean(req, AuthGroup.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(AuthGroupEditParam req) {
        if (!this.exists(new LambdaQueryWrapper<AuthGroup>().eq(AuthGroup::getId, req.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        AuthGroup bean = BeanUtil.toBean(req, AuthGroup.class);
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
    public AuthGroup detail(String id) {
        AuthGroup authGroup = this.getById(id);
        if (ObjectUtil.isEmpty(authGroup)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return authGroup;
    }

    @Override
    public List<AuthGroup> latest(int n) {
        return this.list(new QueryWrapper<AuthGroup>()
            .lambda()
            .orderByDesc(AuthGroup::getId)
            .last("limit " + n));
    }

    @Override
    public List<AuthGroup> topN(int n) {
        return this.list(new QueryWrapper<AuthGroup>()
            .lambda()
            .orderByDesc(AuthGroup::getId)
            .last("limit " + n));
    }

}