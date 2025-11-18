package io.jiangbyte.app.modules.user.info.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.user.info.entity.UserInfo;
import io.jiangbyte.app.modules.user.info.param.UserInfoAddParam;
import io.jiangbyte.app.modules.user.info.param.UserInfoEditParam;
import io.jiangbyte.app.modules.user.info.param.UserInfoIdParam;
import io.jiangbyte.app.modules.user.info.param.UserInfoPageParam;
import io.jiangbyte.app.modules.user.info.mapper.UserInfoMapper;
import io.jiangbyte.app.modules.user.info.service.UserInfoService;
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
* @description 用户基本信息表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public Page<UserInfo> page(UserInfoPageParam userInfoPageParam) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<UserInfo>().checkSqlInjection();
        if (ObjectUtil.isAllNotEmpty(userInfoPageParam.getSortField(), userInfoPageParam.getSortOrder()) && ISortOrderEnum.isValid(userInfoPageParam.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    userInfoPageParam.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(userInfoPageParam.getSortField()));
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(userInfoPageParam.getCurrent()).orElse(1),
                        Optional.ofNullable(userInfoPageParam.getSize()).orElse(10)
                ),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserInfoAddParam userInfoAddParam) {
        UserInfo bean = BeanUtil.toBean(userInfoAddParam, UserInfo.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(UserInfoEditParam userInfoEditParam) {
        if (!this.exists(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getId, userInfoEditParam.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        UserInfo bean = BeanUtil.toBean(userInfoEditParam, UserInfo.class);
        BeanUtil.copyProperties(userInfoEditParam, bean);
        this.updateById(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<UserInfoIdParam> userInfoIdParamList) {
        if (ObjectUtil.isEmpty(userInfoIdParamList)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        this.removeByIds(CollStreamUtil.toList(userInfoIdParamList, UserInfoIdParam::getId));
    }

    @Override
    public UserInfo detail(UserInfoIdParam userInfoIdParam) {
        UserInfo userInfo = this.getById(userInfoIdParam.getId());
        if (ObjectUtil.isEmpty(userInfo)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return userInfo;
    }

    @Override
    public List<UserInfo> latest(int n) {
        return this.list(new QueryWrapper<UserInfo>()
            .lambda()
            .orderByDesc(UserInfo::getId)
            .last("limit " + n));
    }

    @Override
    public List<UserInfo> topN(int n) {
        return this.list(new QueryWrapper<UserInfo>()
            .lambda()
            .orderByDesc(UserInfo::getId)
            .last("limit " + n));
    }

}