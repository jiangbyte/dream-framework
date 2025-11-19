package io.jiangbyte.app.modules.auth.role.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.util.Date;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 角色菜单关联 编辑参数
*/
@Data
@Schema(name = "AuthRoleMenu", description = "角色菜单关联 编辑参数")
public class AuthRoleMenuEditParam implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "角色ID")
    private String roleId;

    @Schema(description = "菜单ID")
    private String menuId;

}