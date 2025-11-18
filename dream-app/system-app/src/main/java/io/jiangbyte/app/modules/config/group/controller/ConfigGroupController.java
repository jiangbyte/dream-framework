package io.jiangbyte.app.modules.config.group.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.jiangbyte.framework.result.Result;
import io.jiangbyte.app.modules.config.group.param.ConfigGroupPageParam;
import io.jiangbyte.app.modules.config.group.param.ConfigGroupAddParam;
import io.jiangbyte.app.modules.config.group.param.ConfigGroupEditParam;
import io.jiangbyte.app.modules.config.group.param.ConfigGroupIdParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jiangbyte.app.modules.config.group.service.ConfigGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 配置分组表 控制器
*/
@Tag(name = "配置分组表控制器")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class ConfigGroupController {
    private final ConfigGroupService configGroupService;

    @Operation(summary = "获取配置分组分页")
    @SaCheckPermission("/config/group/page")
    @GetMapping("/config/group/page")
    public Result<?> page(@ParameterObject ConfigGroupPageParam configGroupPageParam) {
        return Result.success(configGroupService.page(configGroupPageParam));
    }

    @Operation(summary = "添加配置分组")
    @SaCheckPermission("/config/group/add")
    @PostMapping("/config/group/add")
    public Result<?> add(@RequestBody @Valid ConfigGroupAddParam configGroupAddParam) {
        configGroupService.add(configGroupAddParam);
        return Result.success();
    }

    @Operation(summary = "编辑配置分组")
    @SaCheckPermission("/config/group/edit")
    @PostMapping("/config/group/edit")
    public Result<?> edit(@RequestBody @Valid ConfigGroupEditParam configGroupEditParam) {
        configGroupService.edit(configGroupEditParam);
        return Result.success();
    }

    @Operation(summary = "删除配置分组")
    @SaCheckPermission("/config/group/delete")
    @PostMapping("/config/group/delete")
    public Result<?> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空") List<ConfigGroupIdParam> configGroupIdParam) {
        configGroupService.delete(configGroupIdParam);
        return Result.success();
    }

    @Operation(summary = "获取配置分组详情")
    @SaCheckPermission("/config/group/detail")
    @GetMapping("/config/group/detail")
    public Result<?> detail(@ParameterObject @Valid ConfigGroupIdParam configGroupIdParam) {
        return Result.success(configGroupService.detail(configGroupIdParam));
    }

    @Operation(summary = "获取配置分组N最新")
    @SaCheckPermission("/config/group/latest")
    public Result<?> latest(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(configGroupService.latest(n));
    }

    @Operation(summary = "获取配置分组Top N")
    @SaCheckPermission("/config/group/top")
    @GetMapping("/config/group/top")
    public Result<?> topN(@RequestParam(value = "n", required = false) Integer n) {
        return Result.success(configGroupService.topN(n));
    }

}