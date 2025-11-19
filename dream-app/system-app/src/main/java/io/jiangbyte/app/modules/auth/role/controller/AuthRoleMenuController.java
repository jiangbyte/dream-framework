package io.jiangbyte.app.modules.auth.role.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleMenuPageParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleMenuAddParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleMenuEditParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jiangbyte.app.modules.auth.role.service.AuthRoleMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 角色菜单关联表 控制器
*/
@Tag(name = "角色菜单关联表控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class AuthRoleMenuController {
    private final AuthRoleMenuService authRoleMenuService;

    @Operation(summary = "获取角色菜单关联分页")
    @SaCheckPermission("/auth/role/menu/page")
    @GetMapping("/auth/role/menu/page")
    public Result<?> page(@ParameterObject AuthRoleMenuPageParam req) {
        return Result.success(authRoleMenuService.page(req));
    }

    @Operation(summary = "添加角色菜单关联")
    @SaCheckPermission("/auth/role/menu/add")
    @PostMapping("/auth/role/menu/add")
    public Result<?> add(@RequestBody @Valid AuthRoleMenuAddParam req) {
        authRoleMenuService.add(req);
        return Result.success();
    }

    @Operation(summary = "编辑角色菜单关联")
    @SaCheckPermission("/auth/role/menu/edit")
    @PostMapping("/auth/role/menu/edit")
    public Result<?> edit(@RequestBody @Valid AuthRoleMenuEditParam req) {
        authRoleMenuService.edit(req);
        return Result.success();
    }

    @Operation(summary = "删除角色菜单关联")
    @SaCheckPermission("/auth/role/menu/delete")
    @PostMapping("/auth/role/menu/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<String> ids) {
        authRoleMenuService.delete(ids);
        return Result.success();
    }

    @Operation(summary = "获取角色菜单关联详情")
    @SaCheckPermission("/auth/role/menu/detail")
    @GetMapping("/auth/role/menu/detail/{id}")
    public Result<?> detail(@PathVariable("id") String id) {
        return Result.success(authRoleMenuService.detail(id));
    }

    @Operation(summary = "获取角色菜单关联N最新")
    @SaCheckPermission("/auth/role/menu/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authRoleMenuService.latest(n));
    }

    @Operation(summary = "获取角色菜单关联TopN")
    @SaCheckPermission("/auth/role/menu/top")
    @GetMapping("/auth/role/menu/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authRoleMenuService.topN(n));
    }

}