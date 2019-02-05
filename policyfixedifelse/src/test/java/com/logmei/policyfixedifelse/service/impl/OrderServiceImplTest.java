package com.logmei.policyfixedifelse.service.impl;

import com.logmei.policyfixedifelse.DTO.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl orderService;

    @Test
    public void handler() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setType("2");
        String s = orderService.handler(orderDTO);
        System.out.println(s);
    }
}