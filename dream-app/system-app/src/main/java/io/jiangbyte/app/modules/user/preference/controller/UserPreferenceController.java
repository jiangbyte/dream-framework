package io.jiangbyte.app.modules.user.preference.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.user.preference.param.UserPreferencePageParam;
import io.jiangbyte.app.modules.user.preference.param.UserPreferenceAddParam;
import io.jiangbyte.app.modules.user.preference.param.UserPreferenceEditParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jiangbyte.app.modules.user.preference.service.UserPreferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 用户偏好设置表 控制器
*/
@Tag(name = "用户偏好设置表控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class UserPreferenceController {
    private final UserPreferenceService userPreferenceService;

    @Operation(summary = "获取用户偏好设置分页")
    @SaCheckPermission("/user/preference/page")
    @GetMapping("/user/preference/page")
    public Result<?> page(@ParameterObject UserPreferencePageParam req) {
        return Result.success(userPreferenceService.page(req));
    }

    @Operation(summary = "添加用户偏好设置")
    @SaCheckPermission("/user/preference/add")
    @PostMapping("/user/preference/add")
    public Result<?> add(@RequestBody @Valid UserPreferenceAddParam req) {
        userPreferenceService.add(req);
        return Result.success();
    }

    @Operation(summary = "编辑用户偏好设置")
    @SaCheckPermission("/user/preference/edit")
    @PostMapping("/user/preference/edit")
    public Result<?> edit(@RequestBody @Valid UserPreferenceEditParam req) {
        userPreferenceService.edit(req);
        return Result.success();
    }

    @Operation(summary = "删除用户偏好设置")
    @SaCheckPermission("/user/preference/delete")
    @PostMapping("/user/preference/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<String> ids) {
        userPreferenceService.delete(ids);
        return Result.success();
    }

    @Operation(summary = "获取用户偏好设置详情")
    @SaCheckPermission("/user/preference/detail")
    @GetMapping("/user/preference/detail/{id}")
    public Result<?> detail(@PathVariable("id") String id) {
        return Result.success(userPreferenceService.detail(id));
    }

    @Operation(summary = "获取用户偏好设置N最新")
    @SaCheckPermission("/user/preference/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(userPreferenceService.latest(n));
    }

    @Operation(summary = "获取用户偏好设置TopN")
    @SaCheckPermission("/user/preference/top")
    @GetMapping("/user/preference/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(userPreferenceService.topN(n));
    }

}