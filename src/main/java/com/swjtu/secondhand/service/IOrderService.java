package com.swjtu.secondhand.service;

import com.swjtu.secondhand.entity.Order;

import java.util.List;

public interface IOrderService {

    /**
     * @description: 新增订单
     * @date: 2023/1/17
     * @param:  uid 用户id
                pid 商品订单
                transactionMood 交易方式（0或1）
     * @return: void
     */
    void addOrder(String uid, String pid, String transactionMode);

    /**
     * @description: 查找销售或购买订单
     * @date: 2023/1/17
     * @param:  uid
                id
                paramType（buyer/seller）
     * @return: java.util.List<com.swjtu.secondhand.entity.Order>
     */
    List<Order>findMyOrder(String uid, String paramType);

    /**
     * @description: 改变订单状态
     * @date: 2023/1/17
     * @param:  pid
                newState（0-1 取消、0-2完成）
     * @return: void
     */
    void changeOrderState(String pid, String newState);
}
