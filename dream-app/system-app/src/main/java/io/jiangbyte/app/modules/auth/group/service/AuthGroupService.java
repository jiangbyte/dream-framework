package io.jiangbyte.app.modules.auth.group.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.auth.group.entity.AuthGroup;
import io.jiangbyte.app.modules.auth.group.param.AuthGroupAddParam;
import io.jiangbyte.app.modules.auth.group.param.AuthGroupEditParam;
import io.jiangbyte.app.modules.auth.group.param.AuthGroupIdParam;
import io.jiangbyte.app.modules.auth.group.param.AuthGroupPageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 用户组表 服务类
*/
public interface AuthGroupService extends IService<AuthGroup> {
    Page<AuthGroup> page(AuthGroupPageParam authGroupPageParam);

    void add(AuthGroupAddParam authGroupAddParam);

    void edit(AuthGroupEditParam authGroupEditParam);

    void delete(List<AuthGroupIdParam> authGroupIdParamList);

    AuthGroup detail(AuthGroupIdParam authGroupIdParam);

    List<AuthGroup> latest(int n);

    List<AuthGroup> topN(int n);
}