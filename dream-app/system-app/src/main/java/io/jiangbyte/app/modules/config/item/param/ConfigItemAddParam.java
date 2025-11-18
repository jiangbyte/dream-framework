package io.jiangbyte.app.modules.config.item.param;

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
* @description 系统配置 增加参数
*/
@Data
@Schema(name = "ConfigItem", description = "系统配置 增加参数")
public class ConfigItemAddParam implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "分组ID")
    private String groupId;

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