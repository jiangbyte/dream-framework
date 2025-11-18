package io.jiangbyte.app.modules.auth.account.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountPageParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountAddParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountEditParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountIdParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jiangbyte.app.modules.auth.account.service.AuthAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 核心账户表 控制器
*/
@Tag(name = "核心账户表控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class AuthAccountController {
    private final AuthAccountService authAccountService;

    @Operation(summary = "获取核心账户分页")
    @SaCheckPermission("/auth/account/page")
    @GetMapping("/auth/account/page")
    public Result<?> page(@ParameterObject AuthAccountPageParam authAccountPageParam) {
        return Result.success(authAccountService.page(authAccountPageParam));
    }

    @Operation(summary = "添加核心账户")
    @SaCheckPermission("/auth/account/add")
    @PostMapping("/auth/account/add")
    public Result<?> add(@RequestBody @Valid AuthAccountAddParam authAccountAddParam) {
        authAccountService.add(authAccountAddParam);
        return Result.success();
    }

    @Operation(summary = "编辑核心账户")
    @SaCheckPermission("/auth/account/edit")
    @PostMapping("/auth/account/edit")
    public Result<?> edit(@RequestBody @Valid AuthAccountEditParam authAccountEditParam) {
        authAccountService.edit(authAccountEditParam);
        return Result.success();
    }

    @Operation(summary = "删除核心账户")
    @SaCheckPermission("/auth/account/delete")
    @PostMapping("/auth/account/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<AuthAccountIdParam> authAccountIdParam) {
        authAccountService.delete(authAccountIdParam);
        return Result.success();
    }

    @Operation(summary = "获取核心账户详情")
    @SaCheckPermission("/auth/account/detail")
    @GetMapping("/auth/account/detail")
    public Result<?> detail(@ParameterObject @Valid AuthAccountIdParam authAccountIdParam) {
        return Result.success(authAccountService.detail(authAccountIdParam));
    }

    @Operation(summary = "获取核心账户N最新")
    @SaCheckPermission("/auth/account/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authAccountService.latest(n));
    }

    @Operation(summary = "获取核心账户Top N")
    @SaCheckPermission("/auth/account/top")
    @GetMapping("/auth/account/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authAccountService.topN(n));
    }

}