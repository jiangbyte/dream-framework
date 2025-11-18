package io.jiangbyte.app.modules.system.menu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.system.menu.entity.SysMenu;
import io.jiangbyte.app.modules.system.menu.param.SysMenuAddParam;
import io.jiangbyte.app.modules.system.menu.param.SysMenuEditParam;
import io.jiangbyte.app.modules.system.menu.param.SysMenuIdParam;
import io.jiangbyte.app.modules.system.menu.param.SysMenuPageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 菜单表 服务类
*/
public interface SysMenuService extends IService<SysMenu> {
    Page<SysMenu> page(SysMenuPageParam sysMenuPageParam);

    void add(SysMenuAddParam sysMenuAddParam);

    void edit(SysMenuEditParam sysMenuEditParam);

    void delete(List<SysMenuIdParam> sysMenuIdParamList);

    SysMenu detail(SysMenuIdParam sysMenuIdParam);

    List<SysMenu> latest(int n);

    List<SysMenu> topN(int n);
}