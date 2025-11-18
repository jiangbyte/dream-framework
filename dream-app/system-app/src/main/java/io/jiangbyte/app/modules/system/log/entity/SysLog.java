package io.jiangbyte.app.modules.system.log.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
/**
 * <p>
 * 系统活动日志记录表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("sys_log")
@Schema(name = "SysLog", description = "系统活动日志记录表")
public class SysLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private String id;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String userId;

    /**
     * 操作类型
     */
    @Schema(description = "操作类型")
    private String operation;

    /**
     * 请求方法
     */
    @Schema(description = "请求方法")
    private String method;

    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String params;

    /**
     * IP地址
     */
    @Schema(description = "IP地址")
    private String ip;

    /**
     * 操作时间
     */
    @Schema(description = "操作时间")
    private LocalDateTime operationTime;

    /**
     * 日志分类
     */
    @Schema(description = "日志分类")
    private String category;

    /**
     * 操作模块
     */
    @Schema(description = "操作模块")
    private String module;

    /**
     * 操作描述
     */
    @Schema(description = "操作描述")
    private String description;

    /**
     * 操作状态
     */
    @Schema(description = "操作状态")
    private String status;

    /**
     * 日志消息
     */
    @Schema(description = "日志消息")
    private String message;
}
