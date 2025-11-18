package io.jiangbyte.app.modules.user.preference.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.user.preference.entity.UserPreference;
import io.jiangbyte.app.modules.user.preference.param.UserPreferenceAddParam;
import io.jiangbyte.app.modules.user.preference.param.UserPreferenceEditParam;
import io.jiangbyte.app.modules.user.preference.param.UserPreferenceIdParam;
import io.jiangbyte.app.modules.user.preference.param.UserPreferencePageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 用户偏好设置表 服务类
*/
public interface UserPreferenceService extends IService<UserPreference> {
    Page<UserPreference> page(UserPreferencePageParam userPreferencePageParam);

    void add(UserPreferenceAddParam userPreferenceAddParam);

    void edit(UserPreferenceEditParam userPreferenceEditParam);

    void delete(List<UserPreferenceIdParam> userPreferenceIdParamList);

    UserPreference detail(UserPreferenceIdParam userPreferenceIdParam);

    List<UserPreference> latest(int n);

    List<UserPreference> topN(int n);
}