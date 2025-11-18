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
    public Page<AuthAccount> page(AuthAccountPageParam req) {
        QueryWrapper<AuthAccount> queryWrapper = new QueryWrapper<AuthAccount>().checkSqlInjection();
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
    public void add(AuthAccountAddParam req) {
        AuthAccount bean = BeanUtil.toBean(req, AuthAccount.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(AuthAccountEditParam req) {
        if (!this.exists(new LambdaQueryWrapper<AuthAccount>().eq(AuthAccount::getId, req.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        AuthAccount bean = BeanUtil.toBean(req, AuthAccount.class);
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
    public AuthAccount detail(String id) {
        AuthAccount authAccount = this.getById(id);
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