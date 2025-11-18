package io.jiangbyte.app.modules.auth.account.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
/**
 * <p>
 * 核心账户表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("auth_account")
@Schema(name = "AuthAccount", description = "核心账户表")
public class AuthAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private String id;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 加密后的密码
     */
    @Schema(description = "加密后的密码")
    private String password;

    /**
     * 邮箱地址
     */
    @Schema(description = "邮箱地址")
    private String email;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String telephone;

    /**
     * 账户所属组ID
     */
    @Schema(description = "账户所属组ID")
    private String groupId;

    /**
     * 账户状态
     */
    @Schema(description = "账户状态")
    private Short status;

    /**
     * 密码强度等级
     */
    @Schema(description = "密码强度等级")
    private Short passwordStrength;

    /**
     * 最后修改密码的时间
     */
    @Schema(description = "最后修改密码的时间")
    private Date lastPasswordChange;

    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    private Date lastLoginTime;

    /**
     * 最后登录IP地址
     */
    @Schema(description = "最后登录IP地址")
    private String lastLoginIp;

    /**
     * 登录次数统计
     */
    @Schema(description = "登录次数统计")
    private Integer loginCount;
}
