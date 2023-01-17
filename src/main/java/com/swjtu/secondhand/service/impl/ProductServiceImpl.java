package com.swjtu.secondhand.service.impl;

import com.swjtu.secondhand.entity.Product;
import com.swjtu.secondhand.entity.User;
import com.swjtu.secondhand.mapper.IProductMapper;
import com.swjtu.secondhand.mapper.IUserMapper;
import com.swjtu.secondhand.service.IProductService;
import com.swjtu.secondhand.service.ex.InsertException;
import com.swjtu.secondhand.service.ex.ProductNotFoundException;
import com.swjtu.secondhand.service.ex.UpdateException;
import com.swjtu.secondhand.service.ex.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductMapper productMapper;

    @Autowired
    private IUserMapper userMapper;

    @Override
    public List<Product> getProductList() {
        List<Product> list = productMapper.findAll();
        // 将冗余信息设置为null
        for (Product product : list) {
            product.setSeller(null);
            product.setCategory(null);
            product.setTags(null);
            product.setState(null);
            product.setCreateTime(null);
            product.setUpdateTime(null);
        }
        return list;

    }

    @Override
    public List<Product> getProductListByCategory(String cid) {
        List<Product> list = productMapper.findListByCategory(cid);
        // 将冗余信息设置为null
        for (Product product : list) {
            product.setSeller(null);
            product.setCategory(null);
            product.setTags(null);
            product.setState(null);
            product.setCreateTime(null);
            product.setUpdateTime(null);
        }
        return list;
    }

    @Override
    public Product getProductDetailById(String id) {
        Product product = productMapper.findById(id);
        return product;
    }


    @Override
    public void addProduct(String uid, Product product) {
        // 查询用户信息
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        User result = userMapper.findById(uid);
        // 检查查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }

        // 检查查询结果中的isDelete是否为1
        if (result.getState() == "0") {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        // 补全id
        product.setSeller(uid);
        // 补全日期信息
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        // 补全state
        product.setState("0");
        // 执行插入
        Integer rows = productMapper.insert(product);
        // 判断返回值 错误处理
        if (rows != 1) {
            // 是：抛出UpdateException异常
            throw new InsertException("插入数据时出现未知错误，请联系系统管理员");
        }

    }

    @Override
    public void changeProductInfo(String id, Product product) {
        // 查询商品信息
        Product result = productMapper.findById(id);
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new ProductNotFoundException("商品数据不存在");
        }

        // 检查商品状态 不是0表示不在售 不支持修改信息
        if (!result.getState().equals("0")) {
            // 是：抛出UserNotFoundException异常
            System.out.println(result.getState());
            throw new ProductNotFoundException("商品数据不可更改");
        }
        // 补全数据：id
        product.setId(id);
        // 更新日期
        product.setUpdateTime(new Date());
        // 调用方法
        Integer rows = productMapper.updateProductInfo(product);
        // 判断返回值 错误处理
        if (rows != 1) {
            // 是：抛出n异常
            throw new UpdateException("更新数据时出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public void changeProductImg(String id, String img) {
        // 查询商品信息
        Product result = productMapper.findById(id);
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new ProductNotFoundException("商品数据不存在");
        }

        // 检查商品状态 不是0表示不在售 不支持修改信息
        if (!result.getState().equals("0")) {
            // 是：抛出UserNotFoundException异常
            throw new ProductNotFoundException("商品数据不可更改");
        }
        // 创建当前时间对象
        Date now = new Date();
        // 调用方法 获取返回值
        Integer rows = productMapper.updateProductImg(id, img, now);
        if (rows != 1) {
            // 是：抛出n异常
            throw new UpdateException("更新数据时出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public void changeProductState(String id, String newState) {
        // 查询商品信息
        Product result = productMapper.findById(id);
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new ProductNotFoundException("商品数据不存在");
        }

        // 检查商品状态 不是0表示不在售 不支持修改信息
        if (!result.getState().equals("0")) {
            // 是：抛出UserNotFoundException异常
            throw new ProductNotFoundException("商品数据不可更改");
        }

        // 验证指令
        if(newState != "1" && newState != "2"){
            throw new UpdateException("输入数据不合法");
        }

        // 创建当前时间对象
        Date now = new Date();
        // 调用方法 获取返回值
        Integer rows = productMapper.updateProductState(id, newState, now);
        if (rows != 1) {
            // 是：抛出n异常
            throw new UpdateException("更新数据时出现未知错误，请联系系统管理员");
        }

    }
}
