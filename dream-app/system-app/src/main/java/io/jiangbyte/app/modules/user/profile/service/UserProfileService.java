package io.jiangbyte.app.modules.user.profile.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.user.profile.entity.UserProfile;
import io.jiangbyte.app.modules.user.profile.param.UserProfileAddParam;
import io.jiangbyte.app.modules.user.profile.param.UserProfileEditParam;
import io.jiangbyte.app.modules.user.profile.param.UserProfileIdParam;
import io.jiangbyte.app.modules.user.profile.param.UserProfilePageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 用户档案详情表 服务类
*/
public interface UserProfileService extends IService<UserProfile> {
    Page<UserProfile> page(UserProfilePageParam userProfilePageParam);

    void add(UserProfileAddParam userProfileAddParam);

    void edit(UserProfileEditParam userProfileEditParam);

    void delete(List<UserProfileIdParam> userProfileIdParamList);

    UserProfile detail(UserProfileIdParam userProfileIdParam);

    List<UserProfile> latest(int n);

    List<UserProfile> topN(int n);
}