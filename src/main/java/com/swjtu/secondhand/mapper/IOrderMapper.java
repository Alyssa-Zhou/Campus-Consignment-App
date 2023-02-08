package com.swjtu.secondhand.mapper;

import com.swjtu.secondhand.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface IOrderMapper {

    /**
     * @description: 插入新订单
     * @date: 2023/1/15
     * @param: order
     * @return: java.lang.Integer
     */
    Integer insert(Order order);

    /**
     * @description: 根据卖家id查询订单
     * @date: 2023/1/15
     * @param: id
     * @return: java.util.List<com.swjtu.secondhand.entity.Order>
     */
    List<Order> findAllOrderBySellerId(String seller);

    /**
     * @description: 根据买家id查询订单
     * @date: 2023/1/15
     * @param: id
    paramType 指定参数的名称 seller buyer
     * @return: java.util.List<com.swjtu.secondhand.entity.Order>
     */
    List<Order> findAllOrderByBuyerId(String buyer);

    /**
     * @description: 根据订单id查询订单
     * @date: 2023/1/15
     * @param: id
     * @return: com.swjtu.secondhand.entity.Order
     */
    Order findOrderById(String id);

    /**
     * @description: 更新支付订单号
     * @date: 2023/1/15
     * @param: id
    payId
     * @return: java.lang.Integer
     */
    Integer updatePayInfoById(String id, String payId, Date updateTime);

    /**
     * @description: 更新快递号
     * @date: 2023/1/15
     * @param: id
    expressNumber
     * @return: java.lang.Integer
     */
    Integer updateExpressInfoById(String id, String expressNumber, Date updateTime);

    /**
     * @description: 更新订单状态 0-未支付 1-已取消 2-已完成
     * @date: 2023/1/15
     * @param: id
    state
     * @return: java.lang.Integer
     */
    Integer updateStateById(String id, String state, Date updateTime);

}
