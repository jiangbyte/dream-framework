package io.jiangbyte.app.modules.user.profile.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.user.profile.param.UserProfilePageParam;
import io.jiangbyte.app.modules.user.profile.param.UserProfileAddParam;
import io.jiangbyte.app.modules.user.profile.param.UserProfileEditParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jiangbyte.app.modules.user.profile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 用户档案详情表 控制器
*/
@Tag(name = "用户档案详情表控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class UserProfileController {
    private final UserProfileService userProfileService;

    @Operation(summary = "获取用户档案详情分页")
    @SaCheckPermission("/user/profile/page")
    @GetMapping("/user/profile/page")
    public Result<?> page(@ParameterObject UserProfilePageParam req) {
        return Result.success(userProfileService.page(req));
    }

    @Operation(summary = "添加用户档案详情")
    @SaCheckPermission("/user/profile/add")
    @PostMapping("/user/profile/add")
    public Result<?> add(@RequestBody @Valid UserProfileAddParam req) {
        userProfileService.add(req);
        return Result.success();
    }

    @Operation(summary = "编辑用户档案详情")
    @SaCheckPermission("/user/profile/edit")
    @PostMapping("/user/profile/edit")
    public Result<?> edit(@RequestBody @Valid UserProfileEditParam req) {
        userProfileService.edit(req);
        return Result.success();
    }

    @Operation(summary = "删除用户档案详情")
    @SaCheckPermission("/user/profile/delete")
    @PostMapping("/user/profile/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<String> ids) {
        userProfileService.delete(ids);
        return Result.success();
    }

    @Operation(summary = "获取用户档案详情详情")
    @SaCheckPermission("/user/profile/detail")
    @GetMapping("/user/profile/detail/{id}")
    public Result<?> detail(@PathVariable("id") String id) {
        return Result.success(userProfileService.detail(id));
    }

    @Operation(summary = "获取用户档案详情N最新")
    @SaCheckPermission("/user/profile/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(userProfileService.latest(n));
    }

    @Operation(summary = "获取用户档案详情TopN")
    @SaCheckPermission("/user/profile/top")
    @GetMapping("/user/profile/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(userProfileService.topN(n));
    }

}