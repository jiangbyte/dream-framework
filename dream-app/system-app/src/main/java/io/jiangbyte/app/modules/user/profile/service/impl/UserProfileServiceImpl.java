package io.jiangbyte.app.modules.user.profile.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.user.profile.entity.UserProfile;
import io.jiangbyte.app.modules.user.profile.param.UserProfileAddParam;
import io.jiangbyte.app.modules.user.profile.param.UserProfileEditParam;
import io.jiangbyte.app.modules.user.profile.param.UserProfileIdParam;
import io.jiangbyte.app.modules.user.profile.param.UserProfilePageParam;
import io.jiangbyte.app.modules.user.profile.mapper.UserProfileMapper;
import io.jiangbyte.app.modules.user.profile.service.UserProfileService;
import io.jiangbyte.framework.enums.ISortOrderEnum;
import io.jiangbyte.framework.exception.BusinessException;
import io.jiangbyte.framework.pojo.BasePageRequest;
import io.jiangbyte.framework.result.ResultCode;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-06-23
* @description 用户档案详情表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile> implements UserProfileService {

    @Override
    public Page<UserProfile> page(UserProfilePageParam userProfilePageParam) {
        QueryWrapper<UserProfile> queryWrapper = new QueryWrapper<UserProfile>().checkSqlInjection();
        if (ObjectUtil.isAllNotEmpty(userProfilePageParam.getSortField(), userProfilePageParam.getSortOrder()) && ISortOrderEnum.isValid(userProfilePageParam.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    userProfilePageParam.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(userProfilePageParam.getSortField()));
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(userProfilePageParam.getCurrent()).orElse(1),
                        Optional.ofNullable(userProfilePageParam.getSize()).orElse(10)
                ),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserProfileAddParam userProfileAddParam) {
        UserProfile bean = BeanUtil.toBean(userProfileAddParam, UserProfile.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(UserProfileEditParam userProfileEditParam) {
        if (!this.exists(new LambdaQueryWrapper<UserProfile>().eq(UserProfile::getId, userProfileEditParam.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        UserProfile bean = BeanUtil.toBean(userProfileEditParam, UserProfile.class);
        BeanUtil.copyProperties(userProfileEditParam, bean);
        this.updateById(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<UserProfileIdParam> userProfileIdParamList) {
        if (ObjectUtil.isEmpty(userProfileIdParamList)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        this.removeByIds(CollStreamUtil.toList(userProfileIdParamList, UserProfileIdParam::getId));
    }

    @Override
    public UserProfile detail(UserProfileIdParam userProfileIdParam) {
        UserProfile userProfile = this.getById(userProfileIdParam.getId());
        if (ObjectUtil.isEmpty(userProfile)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return userProfile;
    }

    @Override
    public List<UserProfile> latest(int n) {
        return this.list(new QueryWrapper<UserProfile>()
            .lambda()
            .orderByDesc(UserProfile::getId)
            .last("limit " + n));
    }

    @Override
    public List<UserProfile> topN(int n) {
        return this.list(new QueryWrapper<UserProfile>()
            .lambda()
            .orderByDesc(UserProfile::getId)
            .last("limit " + n));
    }

}