package io.jiangbyte.app.modules.system.dict.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.system.dict.entity.SysDict;
import io.jiangbyte.app.modules.system.dict.param.SysDictAddParam;
import io.jiangbyte.app.modules.system.dict.param.SysDictEditParam;
import io.jiangbyte.app.modules.system.dict.param.SysDictPageParam;
import io.jiangbyte.app.modules.system.dict.mapper.SysDictMapper;
import io.jiangbyte.app.modules.system.dict.service.SysDictService;
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
* @description 系统字典表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    @Override
    public Page<SysDict> page(SysDictPageParam req) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<SysDict>().checkSqlInjection();
        if (ObjectUtil.isAllNotEmpty(req.getSortField(), req.getSortOrder()) && ISortOrderEnum.isValid(req.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    req.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(req.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SysDict::getSort);
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(req.getCurrent()).orElse(1),
                        Optional.ofNullable(req.getPageSize()).orElse(10)),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SysDictAddParam req) {
        SysDict bean = BeanUtil.toBean(req, SysDict.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SysDictEditParam req) {
        if (!this.exists(new LambdaQueryWrapper<SysDict>().eq(SysDict::getId, req.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        SysDict bean = BeanUtil.toBean(req, SysDict.class);
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
    public SysDict detail(String id) {
        SysDict sysDict = this.getById(id);
        if (ObjectUtil.isEmpty(sysDict)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return sysDict;
    }

    @Override
    public List<SysDict> latest(int n) {
        return this.list(new QueryWrapper<SysDict>()
            .lambda()
            .orderByDesc(SysDict::getId)
            .last("limit " + n));
    }

    @Override
    public List<SysDict> topN(int n) {
        return this.list(new QueryWrapper<SysDict>()
            .lambda()
            .orderByDesc(SysDict::getId)
            .last("limit " + n));
    }

}