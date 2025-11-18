package io.jiangbyte.app.modules.user.info.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.user.info.entity.UserInfo;
import io.jiangbyte.app.modules.user.info.param.UserInfoAddParam;
import io.jiangbyte.app.modules.user.info.param.UserInfoEditParam;
import io.jiangbyte.app.modules.user.info.param.UserInfoIdParam;
import io.jiangbyte.app.modules.user.info.param.UserInfoPageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 用户基本信息表 服务类
*/
public interface UserInfoService extends IService<UserInfo> {
    Page<UserInfo> page(UserInfoPageParam userInfoPageParam);

    void add(UserInfoAddParam userInfoAddParam);

    void edit(UserInfoEditParam userInfoEditParam);

    void delete(List<UserInfoIdParam> userInfoIdParamList);

    UserInfo detail(UserInfoIdParam userInfoIdParam);

    List<UserInfo> latest(int n);

    List<UserInfo> topN(int n);
}