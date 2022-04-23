package com.geek.edustatistics.controller;


import com.geek.commonutils.result.R;
import com.geek.edustatistics.client.UcenterClient;
import com.geek.edustatistics.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-04-22
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "数据统计管理")
@RequestMapping("/edustatistics/statistics-daily")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService dailyService;

    @ApiOperation("新建/修改某日统计")
    @ApiImplicitParam(name = "date",value = "日期",example = "2022-04-01")
    @PostMapping("createStatistics/{date}")
    public R createStatisticsByDate(@PathVariable("date") String date){
        dailyService.createStatistics(date);
        return R.ok();
    }

    @ApiOperation("根据条件展示统计数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "begin",value = "开始日期"),
            @ApiImplicitParam(name = "end",value = "结束日期"),
    })
    @GetMapping("showChart/{begin}/{end}")
    public R showChart(@PathVariable("begin") String begin,
                       @PathVariable("end") String end){
        if (StringUtils.isAnyBlank(begin,end)) {
            log.info("部分参数为空,{},{}",begin,end);
            return R.error().message("部分参数为空");
        }
        log.info("begin:{},end:{}",begin,end);
        Map<String,Object> map = dailyService.getChartStatistics(begin,end);
        return R.ok().data(map);
    }


}

