package io.jiangbyte.app.modules.auth.account.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.auth.account.entity.AuthAccount;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountAddParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountEditParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountPageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 核心账户表 服务类
*/
public interface AuthAccountService extends IService<AuthAccount> {
    Page<AuthAccount> page(AuthAccountPageParam req);

    void add(AuthAccountAddParam req);

    void edit(AuthAccountEditParam req);

    void delete(List<String> ids);

    AuthAccount detail(String id);

    List<AuthAccount> latest(int n);

    List<AuthAccount> topN(int n);
}