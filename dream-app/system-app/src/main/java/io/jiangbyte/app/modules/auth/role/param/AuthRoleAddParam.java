package io.jiangbyte.app.modules.auth.role.param;

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
* @description 角色 增加参数
*/
@Data
@Schema(name = "AuthRole", description = "角色 增加参数")
public class AuthRoleAddParam implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "是否删除")
    private Boolean isDeleted;

    @Schema(description = "软删除时间")
    private Date deletedAt;

    @Schema(description = "删除操作人")
    private String deleteUser;

    @Schema(description = "创建时间")
    private Date createdAt;

    @Schema(description = "更新时间")
    private Date updatedAt;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色编码")
    private String code;

    @Schema(description = "数据权限范围")
    private String dataScope;

    @Schema(description = "角色描述")
    private String description;

    @Schema(description = "分配的用户组ID列表")
    private Object assignGroupIds;

}