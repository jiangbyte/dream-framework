package io.jiangbyte.app.modules.user.stats.param;

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
* @description 用户统计信息 增加参数
*/
@Data
@Schema(name = "UserStats", description = "用户统计信息 增加参数")
public class UserStatsAddParam implements Serializable {
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

    @Schema(description = "账户ID")
    private String accountId;

    @Schema(description = "等级")
    private Integer level;

    @Schema(description = "经验值")
    private Long exp;

    @Schema(description = "累计经验值")
    private Long totalExp;

    @Schema(description = "登录天数")
    private Integer loginDays;

    @Schema(description = "连续登录天数")
    private Integer continuousLoginDays;

    @Schema(description = "发帖数")
    private Long postCount;

    @Schema(description = "评论数")
    private Long commentCount;

    @Schema(description = "获赞数")
    private Long likeCount;

    @Schema(description = "关注数")
    private Long followCount;

    @Schema(description = "粉丝数")
    private Long fansCount;

}