package io.jiangbyte.app.modules.user.preference.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.util.Date;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 用户偏好设置 编辑参数
*/
@Data
@Schema(name = "UserPreference", description = "用户偏好设置 编辑参数")
public class UserPreferenceEditParam implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private String id;

    private Boolean isDeleted;

    @Schema(description = "软删除时间")
    private Date deletedAt;

    @Schema(description = "删除操作人")
    private String deleteUser;

    @Schema(description = "创建时间")
    private Date createdAt;

    @Schema(description = "更新时间")
    private Date updatedAt;

    @Schema(description = "账户ID")
    private String accountId;

    @Schema(description = "主题")
    private String theme;

    @Schema(description = "系统语言")
    private String language;

    @Schema(description = "邮件通知")
    private Boolean emailNotifications;

    @Schema(description = "推送通知")
    private Boolean pushNotifications;

    @Schema(description = "允许私信")
    private Boolean allowDirectMessage;

}