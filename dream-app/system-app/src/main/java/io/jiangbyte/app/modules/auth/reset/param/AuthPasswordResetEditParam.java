package io.jiangbyte.app.modules.auth.reset.param;

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
* @date 2025-11-18
* @description 密码重置 编辑参数
*/
@Data
@Schema(name = "AuthPasswordReset", description = "密码重置 编辑参数")
public class AuthPasswordResetEditParam implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
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