package io.jiangbyte.app.modules.system.dict.param;

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
* @description 系统字典 增加参数
*/
@Data
@Schema(name = "SysDict", description = "系统字典 增加参数")
public class SysDictAddParam implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "类型标签")
    private String typeLabel;

    @Schema(description = "字典值")
    private String dictValue;

    @Schema(description = "字典标签")
    private String dictLabel;

    @Schema(description = "排序号")
    private Integer sort;

}