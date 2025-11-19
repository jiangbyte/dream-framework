package io.jiangbyte.app.modules.auth.reset.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.auth.reset.param.AuthPasswordResetPageParam;
import io.jiangbyte.app.modules.auth.reset.param.AuthPasswordResetAddParam;
import io.jiangbyte.app.modules.auth.reset.param.AuthPasswordResetEditParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jiangbyte.app.modules.auth.reset.service.AuthPasswordResetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 密码重置表 控制器
*/
@Tag(name = "密码重置表控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class AuthPasswordResetController {
    private final AuthPasswordResetService authPasswordResetService;

    @Operation(summary = "获取密码重置分页")
    @SaCheckPermission("/auth/password/reset/page")
    @GetMapping("/auth/password/reset/page")
    public Result<?> page(@ParameterObject AuthPasswordResetPageParam req) {
        return Result.success(authPasswordResetService.page(req));
    }

    @Operation(summary = "添加密码重置")
    @SaCheckPermission("/auth/password/reset/add")
    @PostMapping("/auth/password/reset/add")
    public Result<?> add(@RequestBody @Valid AuthPasswordResetAddParam req) {
        authPasswordResetService.add(req);
        return Result.success();
    }

    @Operation(summary = "编辑密码重置")
    @SaCheckPermission("/auth/password/reset/edit")
    @PostMapping("/auth/password/reset/edit")
    public Result<?> edit(@RequestBody @Valid AuthPasswordResetEditParam req) {
        authPasswordResetService.edit(req);
        return Result.success();
    }

    @Operation(summary = "删除密码重置")
    @SaCheckPermission("/auth/password/reset/delete")
    @PostMapping("/auth/password/reset/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<String> ids) {
        authPasswordResetService.delete(ids);
        return Result.success();
    }

    @Operation(summary = "获取密码重置详情")
    @SaCheckPermission("/auth/password/reset/detail")
    @GetMapping("/auth/password/reset/detail/{id}")
    public Result<?> detail(@PathVariable("id") String id) {
        return Result.success(authPasswordResetService.detail(id));
    }

    @Operation(summary = "获取密码重置N最新")
    @SaCheckPermission("/auth/password/reset/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authPasswordResetService.latest(n));
    }

    @Operation(summary = "获取密码重置TopN")
    @SaCheckPermission("/auth/password/reset/top")
    @GetMapping("/auth/password/reset/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authPasswordResetService.topN(n));
    }

}