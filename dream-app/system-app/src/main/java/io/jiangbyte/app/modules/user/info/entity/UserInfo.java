package io.jiangbyte.app.modules.user.info.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
/**
 * <p>
 * 用户基本信息表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("user_info")
@Schema(name = "UserInfo", description = "用户基本信息表")
public class UserInfo extends BaseEntity {

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
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    /**
     * 性别：0-未知 1-男 2-女
     */
    @Schema(description = "性别：0-未知 1-男 2-女")
    private Short gender;

    /**
     * 生日
     */
    @Schema(description = "生日")
    private LocalDate birthday;

    /**
     * 个性签名
     */
    @Schema(description = "个性签名")
    private String signature;

    /**
     * 个人背景图片
     */
    @Schema(description = "个人背景图片")
    private String background;

    /**
     * 兴趣标签
     */
    @Schema(description = "兴趣标签")
    private Object interests;

    /**
     * 个人网站
     */
    @Schema(description = "个人网站")
    private String website;

    /**
     * GitHub
     */
    @Schema(description = "GitHub")
    private String github;

    /**
     * GitTee
     */
    @Schema(description = "GitTee")
    private String gitee;

    /**
     * 博客
     */
    @Schema(description = "博客")
    private String blog;
}
