package io.jiangbyte.app.modules.auths.reset.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.auths.reset.entity.AuthsPasswordReset;
import io.jiangbyte.app.modules.auths.reset.dto.AuthsPasswordResetDto;
import io.jiangbyte.app.modules.auths.reset.dto.AuthsPasswordResetPageQuery;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-25
* @description 密码重置表 服务类
*/
public interface AuthsPasswordResetService extends IService<AuthsPasswordReset> {
    Page<AuthsPasswordReset> page(AuthsPasswordResetPageQuery req);

    void add(AuthsPasswordResetDto req);

    void edit(AuthsPasswordResetDto req);

    void delete(List<String> ids);

    AuthsPasswordReset detail(String id);

    List<AuthsPasswordReset> latest(int n);

    List<AuthsPasswordReset> topN(int n);
}