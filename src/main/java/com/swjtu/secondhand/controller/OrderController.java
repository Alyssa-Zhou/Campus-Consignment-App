package com.swjtu.secondhand.controller;

import com.swjtu.secondhand.entity.Order;
import com.swjtu.secondhand.entity.Product;
import com.swjtu.secondhand.service.IOrderService;
import com.swjtu.secondhand.service.IProductService;
import com.swjtu.secondhand.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class OrderController extends BaseController{

    @Autowired
    private IOrderService orderService;

    // 新建订单
    @RequestMapping(value = "/orders/{pid}",method = RequestMethod.POST)
    public JsonResult<Void> addOrder(@PathVariable("pid") String pid, String trans,HttpSession session) {
        // 从session获取当前用户id
        String uid = getIdFromSession(session);
        orderService.addOrder(uid,pid,trans);
        return new JsonResult<Void>(OK);
    }

    // 查找与我有关的订单
    @RequestMapping(value = "/orders/buy",method = RequestMethod.GET)
    public JsonResult<List<Order>> findMyOrderBuy(HttpSession session) {
        // 从session获取当前用户id
        String uid = getIdFromSession(session);
        List<Order> data = orderService.findMyOrder(uid,"buyer");
        return new JsonResult<List<Order>>(OK,data);
    }

    @RequestMapping(value = "/orders/sell",method = RequestMethod.GET)
    public JsonResult<List<Order>> findMyOrderSell(HttpSession session) {
        // 从session获取当前用户id
        String uid = getIdFromSession(session);
        List<Order> data = orderService.findMyOrder(uid,"seller");
        return new JsonResult<List<Order>>(OK,data);
    }
}
