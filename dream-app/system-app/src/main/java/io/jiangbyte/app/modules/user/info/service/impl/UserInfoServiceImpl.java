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
    public Page<UserInfo> page(UserInfoPageParam req) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<UserInfo>().checkSqlInjection();
        if (ObjectUtil.isAllNotEmpty(req.getSortField(), req.getSortOrder()) && ISortOrderEnum.isValid(req.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    req.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(req.getSortField()));
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(req.getCurrent()).orElse(1),
                        Optional.ofNullable(req.getPageSize()).orElse(10)),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserInfoAddParam req) {
        UserInfo bean = BeanUtil.toBean(req, UserInfo.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(UserInfoEditParam req) {
        if (!this.exists(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getId, req.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        UserInfo bean = BeanUtil.toBean(req, UserInfo.class);
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
    public UserInfo detail(String id) {
        UserInfo userInfo = this.getById(id);
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