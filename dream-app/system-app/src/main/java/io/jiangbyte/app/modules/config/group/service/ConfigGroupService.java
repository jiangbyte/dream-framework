package io.jiangbyte.app.modules.config.group.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.config.group.entity.ConfigGroup;
import io.jiangbyte.app.modules.config.group.param.ConfigGroupAddParam;
import io.jiangbyte.app.modules.config.group.param.ConfigGroupEditParam;
import io.jiangbyte.app.modules.config.group.param.ConfigGroupPageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 配置分组表 服务类
*/
public interface ConfigGroupService extends IService<ConfigGroup> {
    Page<ConfigGroup> page(ConfigGroupPageParam req);

    void add(ConfigGroupAddParam req);

    void edit(ConfigGroupEditParam req);

    void delete(List<String> ids);

    ConfigGroup detail(String id);

    List<ConfigGroup> latest(int n);

    List<ConfigGroup> topN(int n);
}