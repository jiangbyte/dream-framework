package io.jiangbyte.app.modules.auth.account.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountRolePageParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountRoleAddParam;
import io.jiangbyte.app.modules.auth.account.param.AuthAccountRoleEditParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jiangbyte.app.modules.auth.account.service.AuthAccountRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 账户角色关联表 控制器
*/
@Tag(name = "账户角色关联表控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class AuthAccountRoleController {
    private final AuthAccountRoleService authAccountRoleService;

    @Operation(summary = "获取账户角色关联分页")
    @SaCheckPermission("/auth/account/role/page")
    @GetMapping("/auth/account/role/page")
    public Result<?> page(@ParameterObject AuthAccountRolePageParam req) {
        return Result.success(authAccountRoleService.page(req));
    }

    @Operation(summary = "添加账户角色关联")
    @SaCheckPermission("/auth/account/role/add")
    @PostMapping("/auth/account/role/add")
    public Result<?> add(@RequestBody @Valid AuthAccountRoleAddParam req) {
        authAccountRoleService.add(req);
        return Result.success();
    }

    @Operation(summary = "编辑账户角色关联")
    @SaCheckPermission("/auth/account/role/edit")
    @PostMapping("/auth/account/role/edit")
    public Result<?> edit(@RequestBody @Valid AuthAccountRoleEditParam req) {
        authAccountRoleService.edit(req);
        return Result.success();
    }

    @Operation(summary = "删除账户角色关联")
    @SaCheckPermission("/auth/account/role/delete")
    @PostMapping("/auth/account/role/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<String> ids) {
        authAccountRoleService.delete(ids);
        return Result.success();
    }

    @Operation(summary = "获取账户角色关联详情")
    @SaCheckPermission("/auth/account/role/detail")
    @GetMapping("/auth/account/role/detail/{id}")
    public Result<?> detail(@PathVariable("id") String id) {
        return Result.success(authAccountRoleService.detail(id));
    }

    @Operation(summary = "获取账户角色关联N最新")
    @SaCheckPermission("/auth/account/role/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authAccountRoleService.latest(n));
    }

    @Operation(summary = "获取账户角色关联TopN")
    @SaCheckPermission("/auth/account/role/top")
    @GetMapping("/auth/account/role/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authAccountRoleService.topN(n));
    }

}