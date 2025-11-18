package io.jiangbyte.app.modules.system.menu.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.system.menu.param.SysMenuPageParam;
import io.jiangbyte.app.modules.system.menu.param.SysMenuAddParam;
import io.jiangbyte.app.modules.system.menu.param.SysMenuEditParam;
import io.jiangbyte.app.modules.system.menu.param.SysMenuIdParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jiangbyte.app.modules.system.menu.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 菜单表 控制器
*/
@Tag(name = "菜单表控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class SysMenuController {
    private final SysMenuService sysMenuService;

    @Operation(summary = "获取菜单分页")
    @SaCheckPermission("/sys/menu/page")
    @GetMapping("/sys/menu/page")
    public Result<?> page(@ParameterObject SysMenuPageParam sysMenuPageParam) {
        return Result.success(sysMenuService.page(sysMenuPageParam));
    }

    @Operation(summary = "添加菜单")
    @SaCheckPermission("/sys/menu/add")
    @PostMapping("/sys/menu/add")
    public Result<?> add(@RequestBody @Valid SysMenuAddParam sysMenuAddParam) {
        sysMenuService.add(sysMenuAddParam);
        return Result.success();
    }

    @Operation(summary = "编辑菜单")
    @SaCheckPermission("/sys/menu/edit")
    @PostMapping("/sys/menu/edit")
    public Result<?> edit(@RequestBody @Valid SysMenuEditParam sysMenuEditParam) {
        sysMenuService.edit(sysMenuEditParam);
        return Result.success();
    }

    @Operation(summary = "删除菜单")
    @SaCheckPermission("/sys/menu/delete")
    @PostMapping("/sys/menu/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<SysMenuIdParam> sysMenuIdParam) {
        sysMenuService.delete(sysMenuIdParam);
        return Result.success();
    }

    @Operation(summary = "获取菜单详情")
    @SaCheckPermission("/sys/menu/detail")
    @GetMapping("/sys/menu/detail")
    public Result<?> detail(@ParameterObject @Valid SysMenuIdParam sysMenuIdParam) {
        return Result.success(sysMenuService.detail(sysMenuIdParam));
    }

    @Operation(summary = "获取菜单N最新")
    @SaCheckPermission("/sys/menu/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(sysMenuService.latest(n));
    }

    @Operation(summary = "获取菜单Top N")
    @SaCheckPermission("/sys/menu/top")
    @GetMapping("/sys/menu/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(sysMenuService.topN(n));
    }

}