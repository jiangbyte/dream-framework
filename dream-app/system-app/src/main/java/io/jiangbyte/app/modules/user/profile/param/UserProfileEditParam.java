package io.jiangbyte.app.modules.user.profile.param;

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
* @description 用户档案详情 编辑参数
*/
@Data
@Schema(name = "UserProfile", description = "用户档案详情 编辑参数")
public class UserProfileEditParam implements Serializable {
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

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "学校")
    private String school;

    @Schema(description = "专业")
    private String major;

    @Schema(description = "学号")
    private String studentId;

    @Schema(description = "公司")
    private String company;

    @Schema(description = "职位")
    private String jobTitle;

    @Schema(description = "行业")
    private String industry;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "省份")
    private String province;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "详细地址")
    private String location;

    @Schema(description = "QQ")
    private String qq;

    @Schema(description = "微信")
    private String wechat;

    @Schema(description = "是否显示生日")
    private Boolean showBirthday;

    @Schema(description = "是否显示地理位置")
    private Boolean showLocation;

}