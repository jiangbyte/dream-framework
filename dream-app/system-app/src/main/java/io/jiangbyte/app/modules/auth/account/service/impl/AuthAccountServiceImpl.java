package io.jiangbyte.app.modules.auth.account.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.auth.account.entity.AuthAccount;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountAddParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountEditParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountIdParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountPageParam;
import io.jiangbyte.app.modules.auth.account.mapper.AuthAccountMapper;
import io.jiangbyte.app.modules.auth.account.service.AuthAccountService;
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
* @description 核心账户表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthAccountServiceImpl extends ServiceImpl<AuthAccountMapper, AuthAccount> implements AuthAccountService {

    @Override
    public Page<AuthAccount> page(AuthAccountPageParam authAccountPageParam) {
        QueryWrapper<AuthAccount> queryWrapper = new QueryWrapper<AuthAccount>().checkSqlInjection();
        if (ObjectUtil.isAllNotEmpty(authAccountPageParam.getSortField(), authAccountPageParam.getSortOrder()) && ISortOrderEnum.isValid(authAccountPageParam.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    authAccountPageParam.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(authAccountPageParam.getSortField()));
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(authAccountPageParam.getCurrent()).orElse(1),
                        Optional.ofNullable(authAccountPageParam.getSize()).orElse(10)
                ),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(AuthAccountAddParam authAccountAddParam) {
        AuthAccount bean = BeanUtil.toBean(authAccountAddParam, AuthAccount.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(AuthAccountEditParam authAccountEditParam) {
        if (!this.exists(new LambdaQueryWrapper<AuthAccount>().eq(AuthAccount::getId, authAccountEditParam.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        AuthAccount bean = BeanUtil.toBean(authAccountEditParam, AuthAccount.class);
        BeanUtil.copyProperties(authAccountEditParam, bean);
        this.updateById(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<AuthAccountIdParam> authAccountIdParamList) {
        if (ObjectUtil.isEmpty(authAccountIdParamList)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        this.removeByIds(CollStreamUtil.toList(authAccountIdParamList, AuthAccountIdParam::getId));
    }

    @Override
    public AuthAccount detail(AuthAccountIdParam authAccountIdParam) {
        AuthAccount authAccount = this.getById(authAccountIdParam.getId());
        if (ObjectUtil.isEmpty(authAccount)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return authAccount;
    }

    @Override
    public List<AuthAccount> latest(int n) {
        return this.list(new QueryWrapper<AuthAccount>()
            .lambda()
            .orderByDesc(AuthAccount::getId)
            .last("limit " + n));
    }

    @Override
    public List<AuthAccount> topN(int n) {
        return this.list(new QueryWrapper<AuthAccount>()
            .lambda()
            .orderByDesc(AuthAccount::getId)
            .last("limit " + n));
    }

}