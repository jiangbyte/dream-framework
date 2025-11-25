package io.jiangbyte.app.modules.access.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.jiangbyte.app.modules.access.dto.*;
import io.jiangbyte.app.modules.access.service.AccessService;
import io.jiangbyte.app.modules.auths.account.entity.AuthsAccount;
import io.jiangbyte.app.modules.auths.account.mapper.AuthsAccountMapper;
import io.jiangbyte.framework.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangJiangHu
 * @version v1.0
 * @date 18/11/2025
 * @description TODO
 */
@Service
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService {
    private final RedisTemplate<String, Object> redisTemplate;

    private final AuthsAccountMapper authsAccountMapper;

    @Override
    public CaptchaResp captcha() {
        CaptchaResp captchaResult = new CaptchaResp();

//        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(100, 38, 4, 10);
//        String imageBase64Data = circleCaptcha.getImageBase64Data();

        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(120, 40, 4, 5);
        lineCaptcha.setGenerator(randomGenerator);
        String imageBase64Data = lineCaptcha.getImageBase64Data();

        captchaResult.setCaptchaImg(imageBase64Data);
        String uuid = IdUtil.fastSimpleUUID();
        captchaResult.setCaptchaId(uuid);
        redisTemplate.opsForValue().set("captcha:" + uuid, lineCaptcha.getCode(), Duration.ofSeconds(5 * 60L));
        return captchaResult;
    }

    @Override
    public LoginResp doLogin(LoginReq loginReq) {
        // 数据库用户名是否存在
        AuthsAccount authAccount = authsAccountMapper.selectOne(new LambdaQueryWrapper<AuthsAccount>().eq(AuthsAccount::getUsername, loginReq.getUsername()));
        if (ObjectUtil.isEmpty(authAccount)) {
            throw new BusinessException("用户不存在");
        }

        // 更新登录时间
        authAccount.setLastLoginTime(new Date());

        authsAccountMapper.updateById(authAccount);

        SaLoginModel loginModel = new SaLoginModel();
        Map<String, Object> extraData = new HashMap<>();
        extraData.put("id", authAccount.getId());
        loginModel.setExtraData(extraData);
        StpUtil.login(authAccount.getId(), loginModel);

        LoginResp loginResp = new LoginResp();
        loginResp.setToken(StpUtil.getTokenValue());
        return loginResp;
    }

    @Override
    public RegisterResp doRegister(RegisterReq registerReq) {
        return null;
    }

    @Override
    public void doLogout() {
        StpUtil.logout();
    }
}
