package io.jiangbyte.app.modules.user.stats.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.user.stats.entity.UserStats;
import io.jiangbyte.app.modules.user.stats.param.UserStatsAddParam;
import io.jiangbyte.app.modules.user.stats.param.UserStatsEditParam;
import io.jiangbyte.app.modules.user.stats.param.UserStatsPageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 用户统计信息表 服务类
*/
public interface UserStatsService extends IService<UserStats> {
    Page<UserStats> page(UserStatsPageParam req);

    void add(UserStatsAddParam req);

    void edit(UserStatsEditParam req);

    void delete(List<String> ids);

    UserStats detail(String id);

    List<UserStats> latest(int n);

    List<UserStats> topN(int n);
}