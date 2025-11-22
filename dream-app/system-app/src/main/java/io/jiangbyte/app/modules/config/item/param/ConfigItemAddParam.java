package io.jiangbyte.app.modules.config.item.param;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.util.Date;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 系统配置 增加参数
*/
@Data
@Schema(name = "ConfigItem", description = "系统配置 增加参数")
public class ConfigItemAddParam implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    private Boolean isDeleted;

    @Schema(description = "软删除时间")
    private Date deletedAt;

    @Schema(description = "删除操作人")
    private String deleteUser;

    @Schema(description = "创建时间")
    private Date createdAt;

    @Schema(description = "更新时间")
    private Date updatedAt;

    @Schema(description = "分组ID")
    private String groupCode;

    @Schema(description = "配置项名称")
    private String name;

    @Schema(description = "配置项代码")
    private String code;

    @Schema(description = "配置值")
    private String value;

    @Schema(description = "组件类型")
    private String componentType;

    @Schema(description = "配置描述")
    private String description;

    @Schema(description = "排序")
    private Integer sort;

}