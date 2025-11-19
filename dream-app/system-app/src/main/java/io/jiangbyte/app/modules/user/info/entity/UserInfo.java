package io.jiangbyte.app.modules.user.info.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serial;
import java.util.Date;
import io.jiangbyte.framework.enums.SortType;
import io.jiangbyte.framework.utils.SortConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 用户基本信息表
*/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "user_info", autoResultMap = true)
@Schema(name = "UserInfo", description = "用户基本信息表")
public class UserInfo extends BaseEntity {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @SortConfig(type = SortType.NUMERIC_STRING)
    private String id;

    @Schema(description = "账户ID")
    private String accountId;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别：0-未知 1-男 2-女")
    private Short gender;

    @Schema(description = "生日")
    private Date birthday;

    @Schema(description = "个性签名")
    private String signature;

    @Schema(description = "个人背景图片")
    private String background;

    @Schema(description = "兴趣标签")
    private Object interests;

    @Schema(description = "个人网站")
    private String website;

    @Schema(description = "GitHub")
    private String github;

    @Schema(description = "GitTee")
    private String gitee;

    @Schema(description = "博客")
    private String blog;
}
