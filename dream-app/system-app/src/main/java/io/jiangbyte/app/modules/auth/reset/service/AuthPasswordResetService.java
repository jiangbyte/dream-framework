package io.jiangbyte.app.modules.auth.reset.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.auth.reset.entity.AuthPasswordReset;
import io.jiangbyte.app.modules.auth.reset.param.AuthPasswordResetAddParam;
import io.jiangbyte.app.modules.auth.reset.param.AuthPasswordResetEditParam;
import io.jiangbyte.app.modules.auth.reset.param.AuthPasswordResetPageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 密码重置表 服务类
*/
public interface AuthPasswordResetService extends IService<AuthPasswordReset> {
    Page<AuthPasswordReset> page(AuthPasswordResetPageParam req);

    void add(AuthPasswordResetAddParam req);

    void edit(AuthPasswordResetEditParam req);

    void delete(List<String> ids);

    AuthPasswordReset detail(String id);

    List<AuthPasswordReset> latest(int n);

    List<AuthPasswordReset> topN(int n);
}