package io.jiangbyte.app.modules.config.group.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import java.io.Serializable;
import java.io.Serial;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 配置分组表
*/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("config_group")
@Schema(name = "ConfigGroup", description = "配置分组表")
public class ConfigGroup extends BaseEntity {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "分组名称")
    private String name;

    @Schema(description = "分组代码")
    private String code;

    @Schema(description = "分组描述")
    private String description;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "是否系统分组")
    private Boolean isSystem;
}
