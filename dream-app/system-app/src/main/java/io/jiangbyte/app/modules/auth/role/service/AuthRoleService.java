package io.jiangbyte.app.modules.auth.role.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.auth.role.entity.AuthRole;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleAddParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleEditParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleIdParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRolePageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 角色表 服务类
*/
public interface AuthRoleService extends IService<AuthRole> {
    Page<AuthRole> page(AuthRolePageParam authRolePageParam);

    void add(AuthRoleAddParam authRoleAddParam);

    void edit(AuthRoleEditParam authRoleEditParam);

    void delete(List<AuthRoleIdParam> authRoleIdParamList);

    AuthRole detail(AuthRoleIdParam authRoleIdParam);

    List<AuthRole> latest(int n);

    List<AuthRole> topN(int n);
}