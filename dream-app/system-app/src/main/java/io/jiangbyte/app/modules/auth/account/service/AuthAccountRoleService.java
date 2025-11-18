package io.jiangbyte.app.modules.auth.account.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.auth.account.entity.AuthAccountRole;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountRoleAddParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountRoleEditParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountRolePageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 账户角色关联表 服务类
*/
public interface AuthAccountRoleService extends IService<AuthAccountRole> {
    Page<AuthAccountRole> page(AuthAccountRolePageParam req);

    void add(AuthAccountRoleAddParam req);

    void edit(AuthAccountRoleEditParam req);

    void delete(List<String> ids);

    AuthAccountRole detail(String id);

    List<AuthAccountRole> latest(int n);

    List<AuthAccountRole> topN(int n);
}