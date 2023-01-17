package com.swjtu.secondhand.service;

import com.swjtu.secondhand.entity.Order;
import com.swjtu.secondhand.entity.Product;
import com.swjtu.secondhand.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;

    @Test
    public void addOrder() {
        try {
            orderService.addOrder("0000000014","00000000000000000011","0");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findMyOrder() {
        try {
            List<Order> list = orderService.findMyOrder("0000000014","seller");
            System.out.println(list.size());
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
