package io.jiangbyte.app.modules.auth.group.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 用户组表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("auth_group")
@Schema(name = "AuthGroup", description = "用户组表")
public class AuthGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private String id;

    /**
     * 父级组ID
     */
    @Schema(description = "父级组ID")
    private String parentId;

    /**
     * 用户组名称
     */
    @Schema(description = "用户组名称")
    private String name;

    /**
     * 用户组编码
     */
    @Schema(description = "用户组编码")
    private String code;

    /**
     * 用户组描述
     */
    @Schema(description = "用户组描述")
    private String description;

    /**
     * 排序号
     */
    @Schema(description = "排序号")
    private Short sort;

    /**
     * 管理员ID
     */
    @Schema(description = "管理员ID")
    private String adminId;

    /**
     * 最大用户数量限制
     */
    @Schema(description = "最大用户数量限制")
    private Integer maxUserCount;

    /**
     * 是否为系统预设组
     */
    @Schema(description = "是否为系统预设组")
    private Boolean isSystem;
}
