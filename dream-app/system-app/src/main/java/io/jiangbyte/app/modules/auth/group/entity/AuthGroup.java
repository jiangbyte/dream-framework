package io.jiangbyte.app.modules.auth.group.entity;

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
* @description 用户组表
*/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "auth_group", autoResultMap = true)
@Schema(name = "AuthGroup", description = "用户组表")
public class AuthGroup extends BaseEntity {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @SortConfig(type = SortType.NUMERIC_STRING)
    private String id;

    @Schema(description = "父级组ID")
    private String parentId;

    @Schema(description = "用户组名称")
    private String name;

    @Schema(description = "用户组编码")
    private String code;

    @Schema(description = "用户组描述")
    private String description;

    @Schema(description = "排序号")
    @SortConfig(type = SortType.NUMERIC_STRING)
    private Short sort;

    @Schema(description = "管理员ID")
    private String adminId;

    @Schema(description = "最大用户数量限制")
    private Integer maxUserCount;

    @Schema(description = "是否为系统预设组")
    private Boolean isSystem;
}
