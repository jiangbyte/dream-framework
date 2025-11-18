package io.jiangbyte.app.modules.auth.reset.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
/**
 * <p>
 * 密码重置表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("auth_password_reset")
@Schema(name = "AuthPasswordReset", description = "密码重置表")
public class AuthPasswordReset extends BaseEntity {

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
     * 重置令牌
     */
    @Schema(description = "重置令牌")
    private String token;

    /**
     * 接收重置邮件的邮箱
     */
    @Schema(description = "接收重置邮件的邮箱")
    private String email;

    /**
     * 令牌过期时间
     */
    @Schema(description = "令牌过期时间")
    private LocalDateTime expiresAt;

    /**
     * 是否已使用
     */
    @Schema(description = "是否已使用")
    private Boolean used;
}
