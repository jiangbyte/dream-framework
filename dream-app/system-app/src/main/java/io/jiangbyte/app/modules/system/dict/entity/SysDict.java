package io.jiangbyte.app.modules.system.dict.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 系统字典表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("sys_dict")
@Schema(name = "SysDict", description = "系统字典表")
public class SysDict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private String id;

    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    private String dictType;

    /**
     * 类型标签
     */
    @Schema(description = "类型标签")
    private String typeLabel;

    /**
     * 字典值
     */
    @Schema(description = "字典值")
    private String dictValue;

    /**
     * 字典标签
     */
    @Schema(description = "字典标签")
    private String dictLabel;

    /**
     * 排序号
     */
    @Schema(description = "排序号")
    private Integer sort;
}
