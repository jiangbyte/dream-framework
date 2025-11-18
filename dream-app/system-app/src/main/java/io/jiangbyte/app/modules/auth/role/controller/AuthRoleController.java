package io.jiangbyte.app.modules.auth.role.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.auth.role.param.AuthRolePageParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleAddParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleEditParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jiangbyte.app.modules.auth.role.service.AuthRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 角色表 控制器
*/
@Tag(name = "角色表控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class AuthRoleController {
    private final AuthRoleService authRoleService;

    @Operation(summary = "获取角色分页")
    @SaCheckPermission("/auth/role/page")
    @GetMapping("/auth/role/page")
    public Result<?> page(@ParameterObject AuthRolePageParam req) {
        return Result.success(authRoleService.page(req));
    }

    @Operation(summary = "添加角色")
    @SaCheckPermission("/auth/role/add")
    @PostMapping("/auth/role/add")
    public Result<?> add(@RequestBody @Valid AuthRoleAddParam req) {
        authRoleService.add(req);
        return Result.success();
    }

    @Operation(summary = "编辑角色")
    @SaCheckPermission("/auth/role/edit")
    @PostMapping("/auth/role/edit")
    public Result<?> edit(@RequestBody @Valid AuthRoleEditParam req) {
        authRoleService.edit(req);
        return Result.success();
    }

    @Operation(summary = "删除角色")
    @SaCheckPermission("/auth/role/delete")
    @PostMapping("/auth/role/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<String> ids) {
        authRoleService.delete(ids);
        return Result.success();
    }

    @Operation(summary = "获取角色详情")
    @SaCheckPermission("/auth/role/detail")
    @GetMapping("/auth/role/detail/{id}")
    public Result<?> detail(@PathVariable("id") String id) {
        return Result.success(authRoleService.detail(id));
    }

    @Operation(summary = "获取角色N最新")
    @SaCheckPermission("/auth/role/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authRoleService.latest(n));
    }

    @Operation(summary = "获取角色Top N")
    @SaCheckPermission("/auth/role/top")
    @GetMapping("/auth/role/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authRoleService.topN(n));
    }

}