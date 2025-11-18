package io.jiangbyte.app.modules.auth.group.param;

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
* @date 2025-11-18
* @description 用户组 编辑参数
*/
@Data
@Schema(name = "AuthGroup", description = "用户组 编辑参数")
public class AuthGroupEditParam implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
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
    private Short sort;

    @Schema(description = "管理员ID")
    private String adminId;

    @Schema(description = "最大用户数量限制")
    private Integer maxUserCount;

    @Schema(description = "是否为系统预设组")
    private Boolean isSystem;

}