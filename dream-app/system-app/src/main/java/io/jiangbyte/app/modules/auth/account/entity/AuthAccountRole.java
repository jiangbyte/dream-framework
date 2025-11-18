package io.jiangbyte.app.modules.auth.account.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 账户角色关联表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("auth_account_role")
@Schema(name = "AuthAccountRole", description = "账户角色关联表")
public class AuthAccountRole extends BaseEntity {

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
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roleId;
}
