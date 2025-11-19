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
import io.jiangbyte.app.modules.user.profile.param.UserProfilePageParam;
import io.jiangbyte.app.modules.user.profile.mapper.UserProfileMapper;
import io.jiangbyte.app.modules.user.profile.service.UserProfileService;
import io.jiangbyte.framework.utils.SortUtils;
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
    public Page<UserProfile> page(UserProfilePageParam req) {
        QueryWrapper<UserProfile> queryWrapper = new QueryWrapper<UserProfile>().checkSqlInjection();
        SortUtils.handleSort(UserProfile.class, queryWrapper, req.getSortField(), req.getSortOrder());
        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(req.getCurrent()).orElse(1),
                        Optional.ofNullable(req.getPageSize()).orElse(10)),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserProfileAddParam req) {
        UserProfile bean = BeanUtil.toBean(req, UserProfile.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(UserProfileEditParam req) {
        if (!this.exists(new LambdaQueryWrapper<UserProfile>().eq(UserProfile::getId, req.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        UserProfile bean = BeanUtil.toBean(req, UserProfile.class);
        BeanUtil.copyProperties(req, bean);
        this.updateById(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<String> ids) {
        if (ObjectUtil.isEmpty(ids)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        this.removeByIds(ids);
    }

    @Override
    public UserProfile detail(String id) {
        UserProfile userProfile = this.getById(id);
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