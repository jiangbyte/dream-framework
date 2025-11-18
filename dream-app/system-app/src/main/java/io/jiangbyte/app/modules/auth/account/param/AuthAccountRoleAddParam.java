package io.jiangbyte.app.modules.auth.account.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.util.Date;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 账户角色关联 增加参数
*/
@Data
@Schema(name = "AuthAccountRole", description = "账户角色关联 增加参数")
public class AuthAccountRoleAddParam implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "账户ID")
    private String accountId;

    @Schema(description = "角色ID")
    private String roleId;

}