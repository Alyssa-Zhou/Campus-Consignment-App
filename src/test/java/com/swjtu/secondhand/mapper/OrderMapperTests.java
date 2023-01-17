package com.swjtu.secondhand.mapper;

import com.swjtu.secondhand.entity.Order;
import com.swjtu.secondhand.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class OrderMapperTests {
    @Autowired
    private IOrderMapper orderMapper;

    @Test
    public void findOrderById() {
        Order result = orderMapper.findOrderById("00000000000000000001");
        System.out.println(result.getBuyer());
    }

    @Test
    public void insert() {
        Order order = new Order();
        order.setSeller("");
        order.setBuyer("");
    }

    @Test
    public void findAllOrderBySellerId() {
        List<Order> result = orderMapper.findAllOrderBySellerId("0000000004");
        System.out.println(result);
    }

    @Test
    public void findAllOrderByBuyerId() {
        List<Order> result = orderMapper.findAllOrderByBuyerId("0000000014");
        System.out.println(result);
    }

    @Test
    public void updatePayInfoById() {
        Integer result = orderMapper.updatePayInfoById("00000000000000000001","12342876412",new Date());
        System.out.println(result);
    }

    @Test
    public void updateExpressInfoById() {
        Integer result = orderMapper.updateExpressInfoById("00000000000000000001","123423785",new Date());
        System.out.println(result);
    }

    @Test
    public void updateStateById() {
        Integer result = orderMapper.updateStateById("00000000000000000001","1",new Date());
        System.out.println(result);
    }
}
