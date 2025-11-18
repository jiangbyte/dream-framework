package io.jiangbyte.app.modules.user.profile.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 用户档案详情表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("user_profile")
@Schema(name = "UserProfile", description = "用户档案详情表")
public class UserProfile extends BaseEntity {

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
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String realName;

    /**
     * 学校
     */
    @Schema(description = "学校")
    private String school;

    /**
     * 专业
     */
    @Schema(description = "专业")
    private String major;

    /**
     * 学号
     */
    @Schema(description = "学号")
    private String studentId;

    /**
     * 公司
     */
    @Schema(description = "公司")
    private String company;

    /**
     * 职位
     */
    @Schema(description = "职位")
    private String jobTitle;

    /**
     * 行业
     */
    @Schema(description = "行业")
    private String industry;

    /**
     * 国家
     */
    @Schema(description = "国家")
    private String country;

    /**
     * 省份
     */
    @Schema(description = "省份")
    private String province;

    /**
     * 城市
     */
    @Schema(description = "城市")
    private String city;

    /**
     * 详细地址
     */
    @Schema(description = "详细地址")
    private String location;

    /**
     * QQ
     */
    @Schema(description = "QQ")
    private String qq;

    /**
     * 微信
     */
    @Schema(description = "微信")
    private String wechat;

    /**
     * 是否显示生日
     */
    @Schema(description = "是否显示生日")
    private Boolean showBirthday;

    /**
     * 是否显示地理位置
     */
    @Schema(description = "是否显示地理位置")
    private Boolean showLocation;
}
