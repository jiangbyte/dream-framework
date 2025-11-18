package io.jiangbyte.app.modules.auth.group.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.auth.group.param.AuthGroupPageParam;
import io.jiangbyte.app.modules.auth.group.param.AuthGroupAddParam;
import io.jiangbyte.app.modules.auth.group.param.AuthGroupEditParam;
import io.jiangbyte.app.modules.auth.group.param.AuthGroupIdParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jiangbyte.app.modules.auth.group.service.AuthGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 用户组表 控制器
*/
@Tag(name = "用户组表控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class AuthGroupController {
    private final AuthGroupService authGroupService;

    @Operation(summary = "获取用户组分页")
    @SaCheckPermission("/auth/group/page")
    @GetMapping("/auth/group/page")
    public Result<?> page(@ParameterObject AuthGroupPageParam authGroupPageParam) {
        return Result.success(authGroupService.page(authGroupPageParam));
    }

    @Operation(summary = "添加用户组")
    @SaCheckPermission("/auth/group/add")
    @PostMapping("/auth/group/add")
    public Result<?> add(@RequestBody @Valid AuthGroupAddParam authGroupAddParam) {
        authGroupService.add(authGroupAddParam);
        return Result.success();
    }

    @Operation(summary = "编辑用户组")
    @SaCheckPermission("/auth/group/edit")
    @PostMapping("/auth/group/edit")
    public Result<?> edit(@RequestBody @Valid AuthGroupEditParam authGroupEditParam) {
        authGroupService.edit(authGroupEditParam);
        return Result.success();
    }

    @Operation(summary = "删除用户组")
    @SaCheckPermission("/auth/group/delete")
    @PostMapping("/auth/group/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<AuthGroupIdParam> authGroupIdParam) {
        authGroupService.delete(authGroupIdParam);
        return Result.success();
    }

    @Operation(summary = "获取用户组详情")
    @SaCheckPermission("/auth/group/detail")
    @GetMapping("/auth/group/detail")
    public Result<?> detail(@ParameterObject @Valid AuthGroupIdParam authGroupIdParam) {
        return Result.success(authGroupService.detail(authGroupIdParam));
    }

    @Operation(summary = "获取用户组N最新")
    @SaCheckPermission("/auth/group/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authGroupService.latest(n));
    }

    @Operation(summary = "获取用户组Top N")
    @SaCheckPermission("/auth/group/top")
    @GetMapping("/auth/group/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(authGroupService.topN(n));
    }

}