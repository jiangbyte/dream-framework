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
import io.jiangbyte.app.modules.auth.group.param.AuthGroupIdParam;
import io.jiangbyte.app.modules.auth.group.param.AuthGroupPageParam;
import io.jiangbyte.app.modules.auth.group.mapper.AuthGroupMapper;
import io.jiangbyte.app.modules.auth.group.service.AuthGroupService;
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
    public Page<AuthGroup> page(AuthGroupPageParam authGroupPageParam) {
        QueryWrapper<AuthGroup> queryWrapper = new QueryWrapper<AuthGroup>().checkSqlInjection();
        // 关键字
        if (ObjectUtil.isNotEmpty(authGroupPageParam.getKeyword())) {
            queryWrapper.lambda().like(AuthGroup::getName, authGroupPageParam.getKeyword());
        }
        if (ObjectUtil.isAllNotEmpty(authGroupPageParam.getSortField(), authGroupPageParam.getSortOrder()) && ISortOrderEnum.isValid(authGroupPageParam.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    authGroupPageParam.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(authGroupPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(AuthGroup::getSort);
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(authGroupPageParam.getCurrent()).orElse(1),
                        Optional.ofNullable(authGroupPageParam.getSize()).orElse(10)
                ),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(AuthGroupAddParam authGroupAddParam) {
        AuthGroup bean = BeanUtil.toBean(authGroupAddParam, AuthGroup.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(AuthGroupEditParam authGroupEditParam) {
        if (!this.exists(new LambdaQueryWrapper<AuthGroup>().eq(AuthGroup::getId, authGroupEditParam.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        AuthGroup bean = BeanUtil.toBean(authGroupEditParam, AuthGroup.class);
        BeanUtil.copyProperties(authGroupEditParam, bean);
        this.updateById(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<AuthGroupIdParam> authGroupIdParamList) {
        if (ObjectUtil.isEmpty(authGroupIdParamList)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        this.removeByIds(CollStreamUtil.toList(authGroupIdParamList, AuthGroupIdParam::getId));
    }

    @Override
    public AuthGroup detail(AuthGroupIdParam authGroupIdParam) {
        AuthGroup authGroup = this.getById(authGroupIdParam.getId());
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