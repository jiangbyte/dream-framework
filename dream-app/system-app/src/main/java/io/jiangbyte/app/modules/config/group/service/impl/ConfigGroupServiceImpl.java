package io.jiangbyte.app.modules.config.group.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.config.group.entity.ConfigGroup;
import io.jiangbyte.app.modules.config.group.param.ConfigGroupAddParam;
import io.jiangbyte.app.modules.config.group.param.ConfigGroupEditParam;
import io.jiangbyte.app.modules.config.group.param.ConfigGroupIdParam;
import io.jiangbyte.app.modules.config.group.param.ConfigGroupPageParam;
import io.jiangbyte.app.modules.config.group.mapper.ConfigGroupMapper;
import io.jiangbyte.app.modules.config.group.service.ConfigGroupService;
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
* @description 配置分组表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigGroupServiceImpl extends ServiceImpl<ConfigGroupMapper, ConfigGroup> implements ConfigGroupService {

    @Override
    public Page<ConfigGroup> page(ConfigGroupPageParam configGroupPageParam) {
        QueryWrapper<ConfigGroup> queryWrapper = new QueryWrapper<ConfigGroup>().checkSqlInjection();
        // 关键字
        if (ObjectUtil.isNotEmpty(configGroupPageParam.getKeyword())) {
            queryWrapper.lambda().like(ConfigGroup::getName, configGroupPageParam.getKeyword());
        }
        if (ObjectUtil.isAllNotEmpty(configGroupPageParam.getSortField(), configGroupPageParam.getSortOrder()) && ISortOrderEnum.isValid(configGroupPageParam.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    configGroupPageParam.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(configGroupPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(ConfigGroup::getSort);
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(configGroupPageParam.getCurrent()).orElse(1),
                        Optional.ofNullable(configGroupPageParam.getSize()).orElse(10)
                ),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ConfigGroupAddParam configGroupAddParam) {
        ConfigGroup bean = BeanUtil.toBean(configGroupAddParam, ConfigGroup.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(ConfigGroupEditParam configGroupEditParam) {
        if (!this.exists(new LambdaQueryWrapper<ConfigGroup>().eq(ConfigGroup::getId, configGroupEditParam.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        ConfigGroup bean = BeanUtil.toBean(configGroupEditParam, ConfigGroup.class);
        BeanUtil.copyProperties(configGroupEditParam, bean);
        this.updateById(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<ConfigGroupIdParam> configGroupIdParamList) {
        if (ObjectUtil.isEmpty(configGroupIdParamList)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        this.removeByIds(CollStreamUtil.toList(configGroupIdParamList, ConfigGroupIdParam::getId));
    }

    @Override
    public ConfigGroup detail(ConfigGroupIdParam configGroupIdParam) {
        ConfigGroup configGroup = this.getById(configGroupIdParam.getId());
        if (ObjectUtil.isEmpty(configGroup)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return configGroup;
    }

    @Override
    public List<ConfigGroup> latest(int n) {
        return this.list(new QueryWrapper<ConfigGroup>()
            .lambda()
            .orderByDesc(ConfigGroup::getId)
            .last("limit " + n));
    }

    @Override
    public List<ConfigGroup> topN(int n) {
        return this.list(new QueryWrapper<ConfigGroup>()
            .lambda()
            .orderByDesc(ConfigGroup::getId)
            .last("limit " + n));
    }

}