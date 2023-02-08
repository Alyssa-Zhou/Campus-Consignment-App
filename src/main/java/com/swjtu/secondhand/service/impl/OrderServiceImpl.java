package com.swjtu.secondhand.service.impl;

import com.swjtu.secondhand.entity.Order;
import com.swjtu.secondhand.entity.Product;
import com.swjtu.secondhand.entity.User;
import com.swjtu.secondhand.entity.UserDetail;
import com.swjtu.secondhand.mapper.IOrderMapper;
import com.swjtu.secondhand.mapper.IProductMapper;
import com.swjtu.secondhand.mapper.IUserDetailMapper;
import com.swjtu.secondhand.mapper.IUserMapper;
import com.swjtu.secondhand.service.IOrderService;
import com.swjtu.secondhand.service.IProductService;
import com.swjtu.secondhand.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderMapper orderMapper;

    @Autowired
    private IProductMapper productMapper;

    @Autowired
    private IUserMapper userMapper;
    @Autowired
    private IUserDetailMapper userDetailMapper;

    @Autowired
    private IProductService productService;


    @Override
    public void addOrder(String uid, String pid, String transactionMode) {
        Product product = productMapper.findById(pid);
        if(product == null){
            throw new ProductNotFoundException("商品数据不存在");
        }
        if(!product.getState().equals("0")){
            throw new ProductNotFoundException("商品数据不存在");
        }

        // 调用userMapper的findById()方法，根据参数id查询用户数据
        User buyer = userMapper.findById(uid);
        // 检查查询结果是否为null
        if (buyer == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        // 检查查询结果中的State是否为0
        if (buyer.getState().equals("0")) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }

        UserDetail buyeruserDetail = userDetailMapper.findById(uid);
        if(buyeruserDetail == null){
            throw new UserNotFoundException("用户数据不存在");
        }

        User seller = userMapper.findById(product.getSeller());
        // 检查查询结果是否为null
        if (seller == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        // 检查查询结果中的State是否为0
        if (seller.getState().equals("0")) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }

        UserDetail selleruserDetail = userDetailMapper.findById(product.getSeller());
        if(selleruserDetail == null){
            throw new UserNotFoundException("用户数据不存在");
        }

        Order order = new Order();
        order.setPid(pid);
        order.setPName(product.getName());
        order.setPrice(product.getPrice());
        order.setSeller(product.getSeller());
        order.setSellerAlias(selleruserDetail.getAlias());
        order.setBuyer(uid);
        order.setBuyerAlias(buyeruserDetail.getAlias());
        order.setTransactionMode(transactionMode);
        order.setState("0");
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());

        Integer rows = orderMapper.insert(order);
        // 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException异常
            throw new InsertException("生成订单时出现未知错误，请联系系统管理员");
        }
        // 下单成功后将商品状态修改为“1-已出售”
        productService.changeProductState(pid,"1");
    }

    @Override
    public List<Order> findMyOrder(String uid, String paramType) {
        if(paramType == "buyer"){
            List<Order> list = orderMapper.findAllOrderByBuyerId(uid);
            return list;
        } else if (paramType == "seller") {
            List<Order> list = orderMapper.findAllOrderBySellerId(uid);
            return list;
        } else {

            return null;
        }
    }

    @Override
    public void changeOrderState(String uid, String id, String newState) {
        Order order = orderMapper.findOrderById(id);
        // 权限核对：只有该订单的卖家和买家可以修改订单状态
        if(uid != order.getBuyer() && uid != order.getSeller()){
            throw new UpdateException("无权修改该订单！");
        }

        if(!order.getState().equals("0")){
            throw new UpdateException("当前订单状态不支持修改！");
        }
        if(newState == "1" || newState == "2"){
            Integer rows = orderMapper.updateStateById(id,newState,new Date());
            // 判断以上返回的受影响行数是否不为1
            if (rows != 1) {
                // 是：抛出UpdateException异常
                throw new UpdateException("订单修改失败，请联系管理员！");
            }
        }
        // 取消订单 商品重新上架
        if(newState == "1"){
            productService.changeProductState(order.getPid(), "0");
        }
    }

    @Override
    public void changeOrderInfo(String uid, String id, String number, String type){
        Order order = orderMapper.findOrderById(id);
        // 权限核对：只有该订单的卖家和买家可以修改订单状态
        if(uid != order.getBuyer() && uid != order.getSeller()){
            throw new UpdateException("无权修改该订单！");
        }
        if(!order.getState().equals("0")){
            throw new UpdateException("当前订单状态不支持修改！");
        }
        Integer rows=0;
        if(type == "1"){// type=1 支付单号
            rows = orderMapper.updatePayInfoById(id,number,new Date());
        }
        else if(type == "2"){// type=2 快递单号
            rows = orderMapper.updateExpressInfoById(id,number,new Date());
        }
        //
        if (rows != 1) {
            // 是：抛出UpdateException异常
            throw new UpdateException("订单修改失败，请联系管理员！");
        }
    }
}
