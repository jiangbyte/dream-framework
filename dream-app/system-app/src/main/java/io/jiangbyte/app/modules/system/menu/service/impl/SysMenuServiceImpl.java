package io.jiangbyte.app.modules.system.menu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.system.menu.entity.SysMenu;
import io.jiangbyte.app.modules.system.menu.param.SysMenuAddParam;
import io.jiangbyte.app.modules.system.menu.param.SysMenuEditParam;
import io.jiangbyte.app.modules.system.menu.param.SysMenuIdParam;
import io.jiangbyte.app.modules.system.menu.param.SysMenuPageParam;
import io.jiangbyte.app.modules.system.menu.mapper.SysMenuMapper;
import io.jiangbyte.app.modules.system.menu.service.SysMenuService;
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
* @description 菜单表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public Page<SysMenu> page(SysMenuPageParam sysMenuPageParam) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<SysMenu>().checkSqlInjection();
        // 关键字
        if (ObjectUtil.isNotEmpty(sysMenuPageParam.getKeyword())) {
            queryWrapper.lambda().like(SysMenu::getName, sysMenuPageParam.getKeyword());
        }
        // 关键字
        if (ObjectUtil.isNotEmpty(sysMenuPageParam.getKeyword())) {
            queryWrapper.lambda().like(SysMenu::getTitle, sysMenuPageParam.getKeyword());
        }
        if (ObjectUtil.isAllNotEmpty(sysMenuPageParam.getSortField(), sysMenuPageParam.getSortOrder()) && ISortOrderEnum.isValid(sysMenuPageParam.getSortOrder())) {
            queryWrapper.orderBy(
                    true,
                    sysMenuPageParam.getSortOrder().equals(ISortOrderEnum.ASCEND.getValue()),
                    StrUtil.toUnderlineCase(sysMenuPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SysMenu::getSort);
        }

        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(sysMenuPageParam.getCurrent()).orElse(1),
                        Optional.ofNullable(sysMenuPageParam.getSize()).orElse(10)
                ),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SysMenuAddParam sysMenuAddParam) {
        SysMenu bean = BeanUtil.toBean(sysMenuAddParam, SysMenu.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SysMenuEditParam sysMenuEditParam) {
        if (!this.exists(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getId, sysMenuEditParam.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        SysMenu bean = BeanUtil.toBean(sysMenuEditParam, SysMenu.class);
        BeanUtil.copyProperties(sysMenuEditParam, bean);
        this.updateById(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SysMenuIdParam> sysMenuIdParamList) {
        if (ObjectUtil.isEmpty(sysMenuIdParamList)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        this.removeByIds(CollStreamUtil.toList(sysMenuIdParamList, SysMenuIdParam::getId));
    }

    @Override
    public SysMenu detail(SysMenuIdParam sysMenuIdParam) {
        SysMenu sysMenu = this.getById(sysMenuIdParam.getId());
        if (ObjectUtil.isEmpty(sysMenu)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return sysMenu;
    }

    @Override
    public List<SysMenu> latest(int n) {
        return this.list(new QueryWrapper<SysMenu>()
            .lambda()
            .orderByDesc(SysMenu::getId)
            .last("limit " + n));
    }

    @Override
    public List<SysMenu> topN(int n) {
        return this.list(new QueryWrapper<SysMenu>()
            .lambda()
            .orderByDesc(SysMenu::getId)
            .last("limit " + n));
    }

}