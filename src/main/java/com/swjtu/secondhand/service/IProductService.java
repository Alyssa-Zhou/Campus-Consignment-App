package com.swjtu.secondhand.service;

import com.swjtu.secondhand.entity.Product;

import java.util.List;

public interface IProductService {

    /**
     * @description: 获取全部在售商品的简要信息
     * @date: 2022/12/11
     * @param:
     * @return: java.util.List<com.swjtu.secondhand.entity.Product>
     */
    public List<Product> getProductList();

    /**
     * @description: 获取所选分类下的所有商品简要信息
     * @date: 2022/12/11
     * @param: cid 分类id
     * @return: java.util.List<com.swjtu.secondhand.entity.Product>
     */
    public List<Product> getProductListByCategory(String cid);

    /**
     * @description: 获取指定商品的详细信息
     * @date: 2022/12/11
     * @param: id 商品id
     * @return: com.swjtu.secondhand.entity.Product
     */
    public Product getProductDetailById(String id);

    /**
     * @description: 新增商品
     * @date: 2022/12/18
     * @param: uid 上传用户id
     * @param: product 商品详情
     * @return: void
     */
    String addProduct(String uid, Product product);

    /**
     * @description: 修改商品信息
     * @date: 2022/12/18
     * @param: id商品id
     * @param: product
     * @return: void
     */
    void changeProductInfo(String id, Product product);

    /**
     * @description: 更新商品图
     * @date: 2022/12/18
     * @param: id 商品id
     * @param: img 图片url
     * @return: void
     */
    void changeProductImg(String id, String img);

    void changeProductState(String id, String newState);
}
