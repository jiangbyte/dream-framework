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
import io.jiangbyte.app.modules.user.preference.param.UserPreferencePageParam;
import io.jiangbyte.app.modules.user.preference.mapper.UserPreferenceMapper;
import io.jiangbyte.app.modules.user.preference.service.UserPreferenceService;
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
* @description 用户偏好设置表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserPreferenceServiceImpl extends ServiceImpl<UserPreferenceMapper, UserPreference> implements UserPreferenceService {

    @Override
    public Page<UserPreference> page(UserPreferencePageParam req) {
        QueryWrapper<UserPreference> queryWrapper = new QueryWrapper<UserPreference>().checkSqlInjection();
        SortUtils.handleSort(UserPreference.class, queryWrapper, req.getSortField(), req.getSortOrder());
        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(req.getCurrent()).orElse(1),
                        Optional.ofNullable(req.getPageSize()).orElse(10)),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserPreferenceAddParam req) {
        UserPreference bean = BeanUtil.toBean(req, UserPreference.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(UserPreferenceEditParam req) {
        if (!this.exists(new LambdaQueryWrapper<UserPreference>().eq(UserPreference::getId, req.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        UserPreference bean = BeanUtil.toBean(req, UserPreference.class);
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
    public UserPreference detail(String id) {
        UserPreference userPreference = this.getById(id);
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