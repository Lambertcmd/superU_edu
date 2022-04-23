package com.geek.edustatistics.service;

import com.geek.edustatistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Lambert
 * @since 2022-04-22
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    /**
     * 新建/修改统计数据
     * @param date
     */
    void createStatistics(String date);

    /**
     * 图标显示统计数据
     * @param begin
     * @param end
     * @param statisticsType
     * @return
     */
    Map<String, Object> getChartStatistics(String begin, String end);
}
