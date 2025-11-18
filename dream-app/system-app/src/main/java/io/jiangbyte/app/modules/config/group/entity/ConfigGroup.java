package io.jiangbyte.app.modules.config.group.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 配置分组表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("config_group")
@Schema(name = "ConfigGroup", description = "配置分组表")
public class ConfigGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private String id;

    /**
     * 分组名称
     */
    @Schema(description = "分组名称")
    private String name;

    /**
     * 分组代码
     */
    @Schema(description = "分组代码")
    private String code;

    /**
     * 分组描述
     */
    @Schema(description = "分组描述")
    private String description;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 是否系统分组
     */
    @Schema(description = "是否系统分组")
    private Boolean isSystem;
}
