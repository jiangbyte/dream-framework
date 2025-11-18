package io.jiangbyte.app.modules.auth.account.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.auth.account.entity.AuthAccount;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountAddParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountEditParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountIdParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountPageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 核心账户表 服务类
*/
public interface AuthAccountService extends IService<AuthAccount> {
    Page<AuthAccount> page(AuthAccountPageParam authAccountPageParam);

    void add(AuthAccountAddParam authAccountAddParam);

    void edit(AuthAccountEditParam authAccountEditParam);

    void delete(List<AuthAccountIdParam> authAccountIdParamList);

    AuthAccount detail(AuthAccountIdParam authAccountIdParam);

    List<AuthAccount> latest(int n);

    List<AuthAccount> topN(int n);
}