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
     * @param:  uid 用户id
     *          id 订单id
                newState（0-1 取消、0-2完成）
     * @return: void
     */
    void changeOrderState(String uid, String id, String newState);

    /**
     * @description: 修改订单信息（支付单号、快递单号）
     * @date: 2023/2/3
     * @param: uid 用户id
                id 订单id
                number 单号
                type 单号类型 1-支付单号 2-快递单号
     * @return: void
     */
    void changeOrderInfo(String uid, String id, String number, String type);

}
