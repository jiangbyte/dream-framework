package io.jiangbyte.app.modules.auth.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.app.SortConfig;
import io.jiangbyte.app.SortType;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 角色表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("auth_role")
@Schema(name = "AuthRole", description = "角色表")
public class AuthRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @SortConfig(type = SortType.NUMERIC_STRING)
    private String id;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String code;

    /**
     * 数据权限范围
     */
    @Schema(description = "数据权限范围")
    private String dataScope;

    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    private String description;

    /**
     * 分配的用户组ID列表
     */
    @Schema(description = "分配的用户组ID列表")
    private Object assignGroupIds;
}
