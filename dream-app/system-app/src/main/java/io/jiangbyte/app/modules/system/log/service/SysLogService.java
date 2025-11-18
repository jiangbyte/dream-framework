package io.jiangbyte.app.modules.system.log.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.jiangbyte.app.modules.system.log.entity.SysLog;
import io.jiangbyte.app.modules.system.log.param.SysLogAddParam;
import io.jiangbyte.app.modules.system.log.param.SysLogEditParam;
import io.jiangbyte.app.modules.system.log.param.SysLogIdParam;
import io.jiangbyte.app.modules.system.log.param.SysLogPageParam;

import java.util.List;

/**
* @author Charlie Zhang
* @version v1.0
* @date 2025-11-18
* @description 系统活动日志记录表 服务类
*/
public interface SysLogService extends IService<SysLog> {
    Page<SysLog> page(SysLogPageParam sysLogPageParam);

    void add(SysLogAddParam sysLogAddParam);

    void edit(SysLogEditParam sysLogEditParam);

    void delete(List<SysLogIdParam> sysLogIdParamList);

    SysLog detail(SysLogIdParam sysLogIdParam);

    List<SysLog> latest(int n);

    List<SysLog> topN(int n);
}