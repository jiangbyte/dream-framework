package io.jiangbyte.app.modules.access.param;

import lombok.Data;

@Data
public class CaptchaResp {
    private String captchaId;
    private String captchaImg;
}