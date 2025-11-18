package io.jiangbyte.app.modules.config.item.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 系统配置表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("config_item")
@Schema(name = "ConfigItem", description = "系统配置表")
public class ConfigItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private String id;

    /**
     * 分组ID
     */
    @Schema(description = "分组ID")
    private String groupId;

    /**
     * 配置项名称
     */
    @Schema(description = "配置项名称")
    private String name;

    /**
     * 配置项代码
     */
    @Schema(description = "配置项代码")
    private String code;

    /**
     * 配置值
     */
    @Schema(description = "配置值")
    private String value;

    /**
     * 组件类型
     */
    @Schema(description = "组件类型")
    private String componentType;

    /**
     * 配置描述
     */
    @Schema(description = "配置描述")
    private String description;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;
}
