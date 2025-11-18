package io.jiangbyte.app.modules.config.item.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.config.item.entity.ConfigItem;
import io.jiangbyte.app.modules.config.item.param.ConfigItemAddParam;
import io.jiangbyte.app.modules.config.item.param.ConfigItemEditParam;
import io.jiangbyte.app.modules.config.item.param.ConfigItemIdParam;
import io.jiangbyte.app.modules.config.item.param.ConfigItemPageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 系统配置表 服务类
*/
public interface ConfigItemService extends IService<ConfigItem> {
    Page<ConfigItem> page(ConfigItemPageParam configItemPageParam);

    void add(ConfigItemAddParam configItemAddParam);

    void edit(ConfigItemEditParam configItemEditParam);

    void delete(List<ConfigItemIdParam> configItemIdParamList);

    ConfigItem detail(ConfigItemIdParam configItemIdParam);

    List<ConfigItem> latest(int n);

    List<ConfigItem> topN(int n);
}