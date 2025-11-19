package io.jiangbyte.app.modules.user.stats.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.user.stats.param.UserStatsPageParam;
import io.jiangbyte.app.modules.user.stats.param.UserStatsAddParam;
import io.jiangbyte.app.modules.user.stats.param.UserStatsEditParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jiangbyte.app.modules.user.stats.service.UserStatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 用户统计信息表 控制器
*/
@Tag(name = "用户统计信息表控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class UserStatsController {
    private final UserStatsService userStatsService;

    @Operation(summary = "获取用户统计信息分页")
    @SaCheckPermission("/user/stats/page")
    @GetMapping("/user/stats/page")
    public Result<?> page(@ParameterObject UserStatsPageParam req) {
        return Result.success(userStatsService.page(req));
    }

    @Operation(summary = "添加用户统计信息")
    @SaCheckPermission("/user/stats/add")
    @PostMapping("/user/stats/add")
    public Result<?> add(@RequestBody @Valid UserStatsAddParam req) {
        userStatsService.add(req);
        return Result.success();
    }

    @Operation(summary = "编辑用户统计信息")
    @SaCheckPermission("/user/stats/edit")
    @PostMapping("/user/stats/edit")
    public Result<?> edit(@RequestBody @Valid UserStatsEditParam req) {
        userStatsService.edit(req);
        return Result.success();
    }

    @Operation(summary = "删除用户统计信息")
    @SaCheckPermission("/user/stats/delete")
    @PostMapping("/user/stats/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<String> ids) {
        userStatsService.delete(ids);
        return Result.success();
    }

    @Operation(summary = "获取用户统计信息详情")
    @SaCheckPermission("/user/stats/detail")
    @GetMapping("/user/stats/detail/{id}")
    public Result<?> detail(@PathVariable("id") String id) {
        return Result.success(userStatsService.detail(id));
    }

    @Operation(summary = "获取用户统计信息N最新")
    @SaCheckPermission("/user/stats/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(userStatsService.latest(n));
    }

    @Operation(summary = "获取用户统计信息TopN")
    @SaCheckPermission("/user/stats/top")
    @GetMapping("/user/stats/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(userStatsService.topN(n));
    }

}