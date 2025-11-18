package io.jiangbyte.app.modules.user.preference.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 用户偏好设置表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("user_preference")
@Schema(name = "UserPreference", description = "用户偏好设置表")
public class UserPreference extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private String id;

    /**
     * 账户ID
     */
    @Schema(description = "账户ID")
    private String accountId;

    /**
     * 主题
     */
    @Schema(description = "主题")
    private String theme;

    /**
     * 系统语言
     */
    @Schema(description = "系统语言")
    private String language;

    /**
     * 邮件通知
     */
    @Schema(description = "邮件通知")
    private Boolean emailNotifications;

    /**
     * 推送通知
     */
    @Schema(description = "推送通知")
    private Boolean pushNotifications;

    /**
     * 允许私信
     */
    @Schema(description = "允许私信")
    private Boolean allowDirectMessage;
}
