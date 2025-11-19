package io.jiangbyte.app.modules.user.info.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.user.info.param.UserInfoPageParam;
import io.jiangbyte.app.modules.user.info.param.UserInfoAddParam;
import io.jiangbyte.app.modules.user.info.param.UserInfoEditParam;
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
* @date 2025-11-19
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
    public Result<?> page(@ParameterObject UserInfoPageParam req) {
        return Result.success(userInfoService.page(req));
    }

    @Operation(summary = "添加用户基本信息")
    @SaCheckPermission("/user/info/add")
    @PostMapping("/user/info/add")
    public Result<?> add(@RequestBody @Valid UserInfoAddParam req) {
        userInfoService.add(req);
        return Result.success();
    }

    @Operation(summary = "编辑用户基本信息")
    @SaCheckPermission("/user/info/edit")
    @PostMapping("/user/info/edit")
    public Result<?> edit(@RequestBody @Valid UserInfoEditParam req) {
        userInfoService.edit(req);
        return Result.success();
    }

    @Operation(summary = "删除用户基本信息")
    @SaCheckPermission("/user/info/delete")
    @PostMapping("/user/info/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<String> ids) {
        userInfoService.delete(ids);
        return Result.success();
    }

    @Operation(summary = "获取用户基本信息详情")
    @SaCheckPermission("/user/info/detail")
    @GetMapping("/user/info/detail/{id}")
    public Result<?> detail(@PathVariable("id") String id) {
        return Result.success(userInfoService.detail(id));
    }

    @Operation(summary = "获取用户基本信息N最新")
    @SaCheckPermission("/user/info/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(userInfoService.latest(n));
    }

    @Operation(summary = "获取用户基本信息TopN")
    @SaCheckPermission("/user/info/top")
    @GetMapping("/user/info/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(userInfoService.topN(n));
    }

}