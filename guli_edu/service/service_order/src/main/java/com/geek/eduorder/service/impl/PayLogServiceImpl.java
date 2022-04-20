package com.geek.eduorder.service.impl;

import com.geek.eduorder.entity.PayLog;
import com.geek.eduorder.mapper.PayLogMapper;
import com.geek.eduorder.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author Lambert
 * @since 2022-04-20
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
