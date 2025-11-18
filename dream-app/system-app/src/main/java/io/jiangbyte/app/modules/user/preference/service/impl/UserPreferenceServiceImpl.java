package io.jiangbyte.app.modules.user.preference.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.user.preference.entity.UserPreference;
import io.jiangbyte.app.modules.user.preference.param.UserPreferenceAddParam;
import io.jiangbyte.app.modules.user.preference.param.UserPreferenceEditParam;
import io.jiangbyte.app.modules.user.preference.param.UserPreferenceIdParam;
import io.jiangbyte.app.modules.user.preference.param.UserPreferencePageParam;
import io.jiangbyte.app.modules.user.preference.mapper.UserPreferenceMapper;
import io.jiangbyte.app.modules.user.preference.service.UserPreferenceService;
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
* @description 用户偏好设置表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserPreferenceServiceImpl extends ServiceImpl<UserPreferenceMapper, UserPreference> implements UserPreferenceService {

    @Override
    public Page<UserPreference> page(UserPreferencePageParam userPreferencePageParam) {
        QueryWrapper<UserPreference> queryWrapper = new QueryWrapper<UserPreference>().checkSqlInjection();
        if (ObjectUtil.isAllNotEmpty(userPreferencePageParam.getSortField(), userPreferencePageParam.getSortOrder()) && ISortOrderEnum.isValid(userPreferencePageParam.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    userPreferencePageParam.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(userPreferencePageParam.getSortField()));
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(userPreferencePageParam.getCurrent()).orElse(1),
                        Optional.ofNullable(userPreferencePageParam.getSize()).orElse(10)
                ),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserPreferenceAddParam userPreferenceAddParam) {
        UserPreference bean = BeanUtil.toBean(userPreferenceAddParam, UserPreference.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(UserPreferenceEditParam userPreferenceEditParam) {
        if (!this.exists(new LambdaQueryWrapper<UserPreference>().eq(UserPreference::getId, userPreferenceEditParam.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        UserPreference bean = BeanUtil.toBean(userPreferenceEditParam, UserPreference.class);
        BeanUtil.copyProperties(userPreferenceEditParam, bean);
        this.updateById(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<UserPreferenceIdParam> userPreferenceIdParamList) {
        if (ObjectUtil.isEmpty(userPreferenceIdParamList)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        this.removeByIds(CollStreamUtil.toList(userPreferenceIdParamList, UserPreferenceIdParam::getId));
    }

    @Override
    public UserPreference detail(UserPreferenceIdParam userPreferenceIdParam) {
        UserPreference userPreference = this.getById(userPreferenceIdParam.getId());
        if (ObjectUtil.isEmpty(userPreference)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return userPreference;
    }

    @Override
    public List<UserPreference> latest(int n) {
        return this.list(new QueryWrapper<UserPreference>()
            .lambda()
            .orderByDesc(UserPreference::getId)
            .last("limit " + n));
    }

    @Override
    public List<UserPreference> topN(int n) {
        return this.list(new QueryWrapper<UserPreference>()
            .lambda()
            .orderByDesc(UserPreference::getId)
            .last("limit " + n));
    }

}