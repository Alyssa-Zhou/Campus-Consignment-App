package com.swjtu.secondhand.mapper;

import com.swjtu.secondhand.entity.Product;
import com.swjtu.secondhand.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface IProductMapper {


    List<Product> findAll();

    Product findById(String id);

    List<Product> findListByCategory(String category);

    Integer insert(Product product);

    Integer updateProductInfo(Product product);

    Integer updateProductImg(String id, String img, Date updateTime);

    Integer updateProductState(String id, String newState, Date updateTime);
}
