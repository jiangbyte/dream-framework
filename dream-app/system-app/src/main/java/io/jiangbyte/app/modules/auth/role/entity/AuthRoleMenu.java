package io.jiangbyte.app.modules.auth.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("auth_role_menu")
@Schema(name = "AuthRoleMenu", description = "角色菜单关联表")
public class AuthRoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private String id;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roleId;

    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    private String menuId;
}
