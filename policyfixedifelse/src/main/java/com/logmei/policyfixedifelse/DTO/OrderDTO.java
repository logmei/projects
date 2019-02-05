package com.logmei.policyfixedifelse.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 22:15 2019/2/2
 * @ Description：订单实体
 * @ Modified By：
 * @Version: 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String code;
    private BigDecimal price;
//    订单类型
//    1、普通订单
//    2、团购订单
//    3、促销订单
    private String type;
}
