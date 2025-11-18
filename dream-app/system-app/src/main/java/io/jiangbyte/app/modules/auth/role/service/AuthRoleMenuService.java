package io.jiangbyte.app.modules.auth.role.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.auth.role.entity.AuthRoleMenu;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleMenuAddParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleMenuEditParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleMenuPageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 角色菜单关联表 服务类
*/
public interface AuthRoleMenuService extends IService<AuthRoleMenu> {
    Page<AuthRoleMenu> page(AuthRoleMenuPageParam req);

    void add(AuthRoleMenuAddParam req);

    void edit(AuthRoleMenuEditParam req);

    void delete(List<String> ids);

    AuthRoleMenu detail(String id);

    List<AuthRoleMenu> latest(int n);

    List<AuthRoleMenu> topN(int n);
}