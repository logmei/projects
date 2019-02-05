package com.logmei.policyfixedifelse.handler;

import com.logmei.policyfixedifelse.DTO.OrderDTO;
import com.logmei.policyfixedifelse.annotation.HandlerType;
import com.logmei.policyfixedifelse.enums.HandlerTypeEnum;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 10:37 2019/2/3
 * @ Description：处理促销订单
 * @ Modified By：
 * @Version: 1.0.0
 */
@Component
@HandlerType("3")
public class PromotionHandler extends AbstractHandler {
    @Override
    public String handler(OrderDTO orderDTO) {
        return HandlerTypeEnum.PROMOTION.getDescription();
    }
}
