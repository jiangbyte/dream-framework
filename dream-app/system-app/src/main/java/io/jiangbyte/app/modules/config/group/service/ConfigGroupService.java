package io.jiangbyte.app.modules.config.group.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.config.group.entity.ConfigGroup;
import io.jiangbyte.app.modules.config.group.dto.ConfigGroupDto;
import io.jiangbyte.app.modules.config.group.dto.ConfigGroupPageQuery;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-25
* @description 配置分组表 服务类
*/
public interface ConfigGroupService extends IService<ConfigGroup> {
    Page<ConfigGroup> page(ConfigGroupPageQuery req);

    void add(ConfigGroupDto req);

    void edit(ConfigGroupDto req);

    void delete(List<String> ids);

    ConfigGroup detail(String id);

    List<ConfigGroup> latest(int n);

    List<ConfigGroup> topN(int n);
}