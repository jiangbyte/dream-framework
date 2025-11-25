package io.jiangbyte.app.modules.auths.reset.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.auths.reset.dto.AuthsPasswordResetDto;
import io.jiangbyte.app.modules.auths.reset.dto.AuthsPasswordResetPageQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jiangbyte.app.modules.auths.reset.service.AuthsPasswordResetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-25
* @description 密码重置表 控制器
*/
@Tag(name = "密码重置表控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class AuthsPasswordResetController {
    private final AuthsPasswordResetService authsPasswordResetService;

    @Operation(summary = "获取密码重置分页")
    @SaCheckPermission("/auths/password/reset/page")
    @GetMapping("/auths/password/reset/page")
    public Result<?> page(@ParameterObject AuthsPasswordResetPageQuery req) {
        return Result.success(authsPasswordResetService.page(req));
    }

    @Operation(summary = "添加密码重置")
    @SaCheckPermission("/auths/password/reset/add")
    @PostMapping("/auths/password/reset/add")
    public Result<?> add(@RequestBody @Valid AuthsPasswordResetDto req) {
        authsPasswordResetService.add(req);
        return Result.success();
    }

    @Operation(summary = "编辑密码重置")
    @SaCheckPermission("/auths/password/reset/edit")
    @PostMapping("/auths/password/reset/edit")
    public Result<?> edit(@RequestBody @Valid AuthsPasswordResetDto req) {
        authsPasswordResetService.edit(req);
        return Result.success();
    }

    @Operation(summary = "删除密码重置")
    @SaCheckPermission("/auths/password/reset/delete")
    @PostMapping("/auths/password/reset/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<String> ids) {
        authsPasswordResetService.delete(ids);
        return Result.success();
    }

    @Operation(summary = "获取密码重置详情")
    @SaCheckPermission("/auths/password/reset/detail")
    @GetMapping("/auths/password/reset/detail/{id}")
    public Result<?> detail(@PathVariable("id") String id) {
        return Result.success(authsPasswordResetService.detail(id));
    }

    @Operation(summary = "获取密码重置N最新")
    @SaCheckPermission("/auths/password/reset/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authsPasswordResetService.latest(n));
    }

    @Operation(summary = "获取密码重置TopN")
    @SaCheckPermission("/auths/password/reset/top")
    @GetMapping("/auths/password/reset/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authsPasswordResetService.topN(n));
    }

}