package com.logmei.policyfixedifelse.handler;

import com.logmei.policyfixedifelse.DTO.OrderDTO;
import com.logmei.policyfixedifelse.annotation.HandlerType;
import com.logmei.policyfixedifelse.enums.HandlerTypeEnum;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 10:36 2019/2/3
 * @ Description：处理团购订单
 * @ Modified By：
 * @Version: 1.0.0
 */
@Component
@HandlerType("2")
public class GroupHandler extends AbstractHandler {
    @Override
    public String handler(OrderDTO orderDTO) {
        return HandlerTypeEnum.GROUP.getDescription();
    }
}
