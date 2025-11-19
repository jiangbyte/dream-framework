package io.jiangbyte.app.modules.auth.role.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jiangbyte.app.modules.auth.role.entity.AuthRoleMenu;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleMenuAddParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleMenuEditParam;
import io.jiangbyte.app.modules.auth.role.param.AuthRoleMenuPageParam;
import io.jiangbyte.app.modules.auth.role.mapper.AuthRoleMenuMapper;
import io.jiangbyte.app.modules.auth.role.service.AuthRoleMenuService;
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
* @description 角色菜单关联表 服务实现类
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthRoleMenuServiceImpl extends ServiceImpl<AuthRoleMenuMapper, AuthRoleMenu> implements AuthRoleMenuService {

    @Override
    public Page<AuthRoleMenu> page(AuthRoleMenuPageParam req) {
        QueryWrapper<AuthRoleMenu> queryWrapper = new QueryWrapper<AuthRoleMenu>().checkSqlInjection();
        SortUtils.handleSort(AuthRoleMenu.class, queryWrapper, req.getSortField(), req.getSortOrder());
        return this.page(BasePageRequest.Page(
                        Optional.ofNullable(req.getCurrent()).orElse(1),
                        Optional.ofNullable(req.getPageSize()).orElse(10)),
                queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(AuthRoleMenuAddParam req) {
        AuthRoleMenu bean = BeanUtil.toBean(req, AuthRoleMenu.class);
        this.save(bean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(AuthRoleMenuEditParam req) {
        if (!this.exists(new LambdaQueryWrapper<AuthRoleMenu>().eq(AuthRoleMenu::getId, req.getId()))) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        AuthRoleMenu bean = BeanUtil.toBean(req, AuthRoleMenu.class);
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
    public AuthRoleMenu detail(String id) {
        AuthRoleMenu authRoleMenu = this.getById(id);
        if (ObjectUtil.isEmpty(authRoleMenu)) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        return authRoleMenu;
    }

    @Override
    public List<AuthRoleMenu> latest(int n) {
        return this.list(new QueryWrapper<AuthRoleMenu>()
            .lambda()
            .orderByDesc(AuthRoleMenu::getId)
            .last("limit " + n));
    }

    @Override
    public List<AuthRoleMenu> topN(int n) {
        return this.list(new QueryWrapper<AuthRoleMenu>()
            .lambda()
            .orderByDesc(AuthRoleMenu::getId)
            .last("limit " + n));
    }

}