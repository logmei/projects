package com.logmei.policyfixedifelse.service.impl;

import com.logmei.policyfixedifelse.DTO.OrderDTO;
import com.logmei.policyfixedifelse.handler.AbstractHandler;
import com.logmei.policyfixedifelse.handler.HandlerContext;
import com.logmei.policyfixedifelse.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 22:20 2019/2/2
 * @ Description：
 * @ Modified By：
 * @Version: 1.0.0
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private HandlerContext handlerContext;
    @Override
    public String handler(OrderDTO orderDTO) {
        AbstractHandler abstractHandler = handlerContext.getHandler(orderDTO.getType());
        return abstractHandler.handler(orderDTO);
    }
}
