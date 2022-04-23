package com.geek.edustatistics.schedule;

import com.geek.edustatistics.service.StatisticsDailyService;
import com.geek.edustatistics.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName ScheduleTask
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/22 23:59
 * @Version 1.0
 **/
@Component
public class ScheduleTask {
    @Autowired
    private StatisticsDailyService dailyService;

    /**
     * 定时任务：每天的凌晨1点执行一次 生成前一天的统计数据
     * cron:使用Cron表达式
     *      cron = "秒、分、时、日、月、周、年" (周指周一周二...周日)
     * ? -> 不指定 * -> 任意 数字 -> 指定 0/1->从每分钟的0秒开始，每1秒执行一次
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void autoCreateStatistics(){
        String date = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        dailyService.createStatistics(date);
    }
}
