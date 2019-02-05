package com.logmei.policyfixedifelse.handler;

import com.logmei.policyfixedifelse.DTO.OrderDTO;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 22:32 2019/2/2
 * @ Description：抽象处理器
 * @ Modified By：
 * @Version: 1.0.0
 */
public abstract class AbstractHandler {
    abstract public String handler(OrderDTO orderDTO);
}
