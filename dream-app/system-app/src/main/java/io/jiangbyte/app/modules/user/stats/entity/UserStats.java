package io.jiangbyte.app.modules.user.stats.entity;

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
* @description 用户统计信息表
*/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("user_stats")
@Schema(name = "UserStats", description = "用户统计信息表")
public class UserStats extends BaseEntity {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private String id;

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
