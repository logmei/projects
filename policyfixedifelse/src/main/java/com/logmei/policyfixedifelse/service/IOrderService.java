package com.logmei.policyfixedifelse.service;

import com.logmei.policyfixedifelse.DTO.OrderDTO;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 22:20 2019/2/2
 * @ Description：接口
 * @ Modified By：
 * @Version: 1.0.0
 */
public interface IOrderService {

    //根据不同订单类型做出不同的处理
    String handler(OrderDTO orderDTO);
}
