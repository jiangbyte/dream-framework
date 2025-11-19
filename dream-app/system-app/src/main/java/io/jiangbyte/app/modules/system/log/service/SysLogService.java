package io.jiangbyte.app.modules.system.log.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.system.log.entity.SysLog;
import io.jiangbyte.app.modules.system.log.param.SysLogAddParam;
import io.jiangbyte.app.modules.system.log.param.SysLogEditParam;
import io.jiangbyte.app.modules.system.log.param.SysLogPageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-19
* @description 系统活动日志记录表 服务类
*/
public interface SysLogService extends IService<SysLog> {
    Page<SysLog> page(SysLogPageParam req);

    void add(SysLogAddParam req);

    void edit(SysLogEditParam req);

    void delete(List<String> ids);

    SysLog detail(String id);

    List<SysLog> latest(int n);

    List<SysLog> topN(int n);
}