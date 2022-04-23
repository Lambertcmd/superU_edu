package com.geek.edustatistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.geek.edustatistics.client.UcenterClient;
import com.geek.edustatistics.entity.StatisticsDaily;
import com.geek.edustatistics.mapper.StatisticsDailyMapper;
import com.geek.edustatistics.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.edustatistics.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Lambert
 * @since 2022-04-22
 */
@Slf4j
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void createStatistics(String date) {

        //获取统计信息
        //当日注册数量
        Integer registerNum = (Integer) ucenterClient.getRegisterCount(date).getData().get("RegisterCount");
        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO

        //创建统计对象
        StatisticsDaily statistics = new StatisticsDaily(date, registerNum, loginNum, videoViewNum, courseNum);
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated", date);
        Long count = baseMapper.selectCount(queryWrapper);
        //若已存在统计对象 更新统计对象
        if (count > 0) {
            log.info("更新统计数据成功！日期:{}",date);
            baseMapper.update(statistics, queryWrapper);
        }else {
            log.info("新增统计数据成功！日期:{}",date);
            baseMapper.insert(statistics);
        }
    }


//    新增测试数据接口
//    public void createStatistics(String date) {
//        for (int i = 0; i < 2000; i++) {
//            Integer registerNum = RandomUtils.nextInt(100, 200);//TODO
//            Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
//            Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
//            Integer courseNum = RandomUtils.nextInt(100, 200);//TODO
//
//            String date1 = DateUtil.formatDate(DateUtil.addDays(new Date(), -i));
//
//            StatisticsDaily statistics = new StatisticsDaily(date1, registerNum, loginNum, videoViewNum, courseNum);
//            QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("date_calculated", date1);
//            Long count = baseMapper.selectCount(queryWrapper);
//            //若已存在统计对象 更新统计对象
//            if (count > 0) {
//                log.info("更新统计数据成功！日期:{}",date1);
//                baseMapper.update(statistics, queryWrapper);
//            }else {
//                log.info("新增统计数据成功！日期:{}",date1);
//                baseMapper.insert(statistics);
//            }
//        }
//
//    }

    @Override
    public Map<String, Object> getChartStatistics(String begin, String end) {
        //根据条件查询对象数据
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated", begin, end);
        List<StatisticsDaily> StatisticsList = baseMapper.selectList(queryWrapper);

        Map<String, Object> map = new HashMap<>();
        List<String> dateCalculatedList = new ArrayList<>();//日期集合
        List<Integer> loginNumList = new ArrayList<>();//学员登录数集合
        List<Integer> registerNumList = new ArrayList<>();//学员注册数集合
        List<Integer> videoViewNumList = new ArrayList<>();//课程播放数集合
        List<Integer> courseNumList = new ArrayList<>();//每日课程数集合
        //给集合封装数据
        StatisticsList.forEach(dailyStatistics -> {
            dateCalculatedList.add(dailyStatistics.getDateCalculated());
            loginNumList.add(dailyStatistics.getLoginNum());
            registerNumList.add(dailyStatistics.getRegisterNum());
            videoViewNumList.add(dailyStatistics.getVideoViewNum());
            courseNumList.add(dailyStatistics.getCourseNum());
        });
        map.put("dateCalculatedList",dateCalculatedList);
        map.put("loginNumList",loginNumList);
        map.put("registerNumList",registerNumList);
        map.put("videoViewNumList",videoViewNumList);
        map.put("courseNumList",courseNumList);
        return map;
    }
}
