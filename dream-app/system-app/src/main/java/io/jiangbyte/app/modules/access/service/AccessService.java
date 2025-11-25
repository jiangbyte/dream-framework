package io.jiangbyte.app.modules.access.service;

import io.jiangbyte.app.modules.access.dto.*;

/**
 * @author ZhangJiangHu
 * @version v1.0
 * @date 18/11/2025
 * @description TODO
 */
public interface AccessService {
    CaptchaResp captcha();

    LoginResp doLogin(LoginReq loginReq);

    RegisterResp doRegister(RegisterReq registerReq);

    void doLogout();
}
