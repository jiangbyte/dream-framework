package io.jiangbyte.app.modules.access.param;

import io.jiangbyte.app.modules.access.entity.UserPublicAssociatedInfo;
import lombok.Data;

@Data
public class LoginResp {
    private String token;
    private UserPublicAssociatedInfo user;
}