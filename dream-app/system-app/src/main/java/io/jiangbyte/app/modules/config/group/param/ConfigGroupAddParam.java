package io.jiangbyte.app.modules.config.group.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.util.Date;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 配置分组 增加参数
*/
@Data
@Schema(name = "ConfigGroup", description = "配置分组 增加参数")
public class ConfigGroupAddParam implements Serializable {
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