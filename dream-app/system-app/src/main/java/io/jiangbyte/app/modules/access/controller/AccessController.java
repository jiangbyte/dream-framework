package io.jiangbyte.app.modules.access.controller;

import io.jiangbyte.app.modules.access.param.*;
import io.jiangbyte.app.modules.access.service.AccessService;
import io.jiangbyte.framework.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangJiangHu
 * @version v1.0
 * @date 18/11/2025
 * @description TODO
 */
@Tag(name = "接入口控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class AccessController {
    private final AccessService accessService;

    @Operation(summary = "获取验证码")
    @GetMapping("/access/captcha")
    public Result<?> captcha() {
        return Result.success(accessService.captcha());
    }

    @Operation(summary = "登录")
    @PostMapping("/access/login")
    public Result<?> login(@RequestBody @Valid LoginReq loginReq) {
        return Result.success(accessService.doLogin(loginReq));
    }

    @Operation(summary = "注册")
    @PostMapping("/access/register")
    public Result<?>  register(@RequestBody @Valid RegisterReq registerReq) {
        return Result.success(accessService.doRegister(registerReq));
    }

}
