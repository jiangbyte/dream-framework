package io.jiangbyte.app.modules.user.stats.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 用户统计信息表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("user_stats")
@Schema(name = "UserStats", description = "用户统计信息表")
public class UserStats extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private String id;

    /**
     * 账户ID
     */
    @Schema(description = "账户ID")
    private String accountId;

    /**
     * 等级
     */
    @Schema(description = "等级")
    private Integer level;

    /**
     * 经验值
     */
    @Schema(description = "经验值")
    private Long exp;

    /**
     * 累计经验值
     */
    @Schema(description = "累计经验值")
    private Long totalExp;

    /**
     * 登录天数
     */
    @Schema(description = "登录天数")
    private Integer loginDays;

    /**
     * 连续登录天数
     */
    @Schema(description = "连续登录天数")
    private Integer continuousLoginDays;

    /**
     * 发帖数
     */
    @Schema(description = "发帖数")
    private Long postCount;

    /**
     * 评论数
     */
    @Schema(description = "评论数")
    private Long commentCount;

    /**
     * 获赞数
     */
    @Schema(description = "获赞数")
    private Long likeCount;

    /**
     * 关注数
     */
    @Schema(description = "关注数")
    private Long followCount;

    /**
     * 粉丝数
     */
    @Schema(description = "粉丝数")
    private Long fansCount;
}
