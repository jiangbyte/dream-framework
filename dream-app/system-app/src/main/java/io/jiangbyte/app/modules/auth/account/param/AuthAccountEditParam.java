package io.jiangbyte.app.modules.auth.account.param;

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
* @description 核心账户 编辑参数
*/
@Data
@Schema(name = "AuthAccount", description = "核心账户 编辑参数")
public class AuthAccountEditParam implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "是否删除")
    private Boolean isDeleted;

    @Schema(description = "软删除时间")
    private Date deletedAt;

    @Schema(description = "删除操作人")
    private String deleteUser;

    @Schema(description = "创建时间")
    private Date createdAt;

    @Schema(description = "更新时间")
    private Date updatedAt;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "加密后的密码")
    private String password;

    @Schema(description = "邮箱地址")
    private String email;

    @Schema(description = "手机号码")
    private String telephone;

    @Schema(description = "账户所属组ID")
    private String groupId;

    @Schema(description = "账户状态")
    private Short status;

    @Schema(description = "密码强度等级")
    private Short passwordStrength;

    @Schema(description = "最后修改密码的时间")
    private Date lastPasswordChange;

    @Schema(description = "最后登录时间")
    private Date lastLoginTime;

    @Schema(description = "最后登录IP地址")
    private String lastLoginIp;

    @Schema(description = "登录次数统计")
    private Integer loginCount;

}