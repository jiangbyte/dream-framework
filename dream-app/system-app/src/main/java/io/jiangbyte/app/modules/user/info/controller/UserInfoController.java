package io.jiangbyte.app.modules.user.info.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.user.info.param.UserInfoPageParam;
import io.jiangbyte.app.modules.user.info.param.UserInfoAddParam;
import io.jiangbyte.app.modules.user.info.param.UserInfoEditParam;
import io.jiangbyte.app.modules.user.info.param.UserInfoIdParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jiangbyte.app.modules.user.info.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 用户基本信息表 控制器
*/
@Tag(name = "用户基本信息表控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class UserInfoController {
    private final UserInfoService userInfoService;

    @Operation(summary = "获取用户基本信息分页")
    @SaCheckPermission("/user/info/page")
    @GetMapping("/user/info/page")
    public Result<?> page(@ParameterObject UserInfoPageParam userInfoPageParam) {
        return Result.success(userInfoService.page(userInfoPageParam));
    }

    @Operation(summary = "添加用户基本信息")
    @SaCheckPermission("/user/info/add")
    @PostMapping("/user/info/add")
    public Result<?> add(@RequestBody @Valid UserInfoAddParam userInfoAddParam) {
        userInfoService.add(userInfoAddParam);
        return Result.success();
    }

    @Operation(summary = "编辑用户基本信息")
    @SaCheckPermission("/user/info/edit")
    @PostMapping("/user/info/edit")
    public Result<?> edit(@RequestBody @Valid UserInfoEditParam userInfoEditParam) {
        userInfoService.edit(userInfoEditParam);
        return Result.success();
    }

    @Operation(summary = "删除用户基本信息")
    @SaCheckPermission("/user/info/delete")
    @PostMapping("/user/info/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<UserInfoIdParam> userInfoIdParam) {
        userInfoService.delete(userInfoIdParam);
        return Result.success();
    }

    @Operation(summary = "获取用户基本信息详情")
    @SaCheckPermission("/user/info/detail")
    @GetMapping("/user/info/detail")
    public Result<?> detail(@ParameterObject @Valid UserInfoIdParam userInfoIdParam) {
        return Result.success(userInfoService.detail(userInfoIdParam));
    }

    @Operation(summary = "获取用户基本信息N最新")
    @SaCheckPermission("/user/info/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(userInfoService.latest(n));
    }

    @Operation(summary = "获取用户基本信息Top N")
    @SaCheckPermission("/user/info/top")
    @GetMapping("/user/info/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(userInfoService.topN(n));
    }

}