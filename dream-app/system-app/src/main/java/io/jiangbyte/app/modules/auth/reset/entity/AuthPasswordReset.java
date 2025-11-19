package io.jiangbyte.app.modules.auth.reset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.io.Serial;
import java.util.Date;
import io.jiangbyte.framework.enums.SortType;
import io.jiangbyte.framework.utils.SortConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 密码重置表
*/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "auth_password_reset", autoResultMap = true)
@Schema(name = "AuthPasswordReset", description = "密码重置表")
public class AuthPasswordReset extends BaseEntity {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @SortConfig(type = SortType.NUMERIC_STRING)
    private String id;

    @Schema(description = "账户ID")
    private String accountId;

    @Schema(description = "重置令牌")
    private String token;

    @Schema(description = "接收重置邮件的邮箱")
    private String email;

    @Schema(description = "令牌过期时间")
    private Date expiresAt;

    @Schema(description = "是否已使用")
    private Boolean used;
}
