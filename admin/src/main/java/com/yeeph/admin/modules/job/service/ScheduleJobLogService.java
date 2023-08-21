/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.yeeph.admin.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.admin.common.utils.PageUtils;
import com.yeeph.admin.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;


public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
