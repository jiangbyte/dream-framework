package io.jiangbyte.app.modules.user.info.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.util.Date;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 用户基本信息 编辑参数
*/
@Data
@Schema(name = "UserInfo", description = "用户基本信息 编辑参数")
public class UserInfoEditParam implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private String id;

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