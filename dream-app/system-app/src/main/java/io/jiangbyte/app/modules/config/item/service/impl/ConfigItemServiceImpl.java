package io.jiangbyte.app.modules.config.item.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.config.item.entity.ConfigItem;
import io.jiangbyte.app.modules.config.item.param.ConfigItemAddParam;
import io.jiangbyte.app.modules.config.item.param.ConfigItemEditParam;
import io.jiangbyte.app.modules.config.item.param.ConfigItemPageParam;
import io.jiangbyte.app.modules.config.item.mapper.ConfigItemMapper;
import io.jiangbyte.app.modules.config.item.service.ConfigItemService;
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
* @description 系统配置表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigItemServiceImpl extends ServiceImpl<ConfigItemMapper, ConfigItem> implements ConfigItemService {

    @Override
    public Page<ConfigItem> page(ConfigItemPageParam req) {
        QueryWrapper<ConfigItem> queryWrapper = new QueryWrapper<ConfigItem>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(req.getKeyword())) {
            queryWrapper.lambda().like(ConfigItem::getName, req.getKeyword());
        }
        if (ObjectUtil.isAllNotEmpty(req.getSortField(), req.getSortOrder()) && ISortOrderEnum.isValid(req.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    req.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(req.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(ConfigItem::getSort);
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(req.getCurrent()).orElse(1),
                        Optional.ofNullable(req.getPageSize()).orElse(10)),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ConfigItemAddParam req) {
        ConfigItem bean = BeanUtil.toBean(req, ConfigItem.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(ConfigItemEditParam req) {
        if (!this.exists(new LambdaQueryWrapper<ConfigItem>().eq(ConfigItem::getId, req.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        ConfigItem bean = BeanUtil.toBean(req, ConfigItem.class);
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
    public ConfigItem detail(String id) {
        ConfigItem configItem = this.getById(id);
        if (ObjectUtil.isEmpty(configItem)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return configItem;
    }

    @Override
    public List<ConfigItem> latest(int n) {
        return this.list(new QueryWrapper<ConfigItem>()
            .lambda()
            .orderByDesc(ConfigItem::getId)
            .last("limit " + n));
    }

    @Override
    public List<ConfigItem> topN(int n) {
        return this.list(new QueryWrapper<ConfigItem>()
            .lambda()
            .orderByDesc(ConfigItem::getId)
            .last("limit " + n));
    }

}