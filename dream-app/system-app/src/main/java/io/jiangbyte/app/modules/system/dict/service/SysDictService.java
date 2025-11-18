package io.jiangbyte.app.modules.system.dict.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.system.dict.entity.SysDict;
import io.jiangbyte.app.modules.system.dict.param.SysDictAddParam;
import io.jiangbyte.app.modules.system.dict.param.SysDictEditParam;
import io.jiangbyte.app.modules.system.dict.param.SysDictIdParam;
import io.jiangbyte.app.modules.system.dict.param.SysDictPageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 系统字典表 服务类
*/
public interface SysDictService extends IService<SysDict> {
    Page<SysDict> page(SysDictPageParam sysDictPageParam);

    void add(SysDictAddParam sysDictAddParam);

    void edit(SysDictEditParam sysDictEditParam);

    void delete(List<SysDictIdParam> sysDictIdParamList);

    SysDict detail(SysDictIdParam sysDictIdParam);

    List<SysDict> latest(int n);

    List<SysDict> topN(int n);
}