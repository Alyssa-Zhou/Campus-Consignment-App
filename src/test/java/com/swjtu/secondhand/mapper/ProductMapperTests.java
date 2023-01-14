package com.swjtu.secondhand.mapper;

import com.swjtu.secondhand.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class ProductMapperTests {

    @Autowired
    private IProductMapper productMapper;

    @Test
    public void findAll() {
        List<Product> result = productMapper.findAll();
        System.out.println(result);
    }

    @Test
    public void findById() {
        String id = "00000000000000000001";
        Product result = productMapper.findById(id);
        System.out.println(result);
    }

    @Test
    public void findListByCategory() {
        String cid = "00008";
        List<Product> result = productMapper.findListByCategory(cid);
        System.out.println(result);
    }

    @Test
    public void insert(){
        Product product = new Product();
        product.setSeller("0000000003");
        product.setName("布洛芬胶囊");
        product.setCategory("00020");
        product.setDescription("abyigurea");
        product.setLocation("1");
        product.setPrice(3.0f);
        product.setTags("布洛芬");
        product.setState("0");
        product.setCreateTime(Date.from(Instant.now()));
        product.setUpdateTime(Date.from(Instant.now()));
        Integer rows =productMapper.insert(product);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateProductInfo(){
        Product product = new Product();
        product.setId("00000000000000000007");
        product.setSeller("0000000003");
        product.setName("布洛芬胶囊");
        product.setCategory("00020");
        product.setDescription("abyigurea");
        product.setLocation("1");
        product.setPrice(3.0f);
        product.setTags("布洛芬");
        product.setState("0");
        product.setCreateTime(Date.from(Instant.now()));
        product.setUpdateTime(Date.from(Instant.now()));
        Integer rows =productMapper.updateProductInfo(product);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateProductImg(){
        Integer rows =productMapper.updateProductImg(
                "00000000000000000007",
                "/upload/pic",
                Date.from(Instant.now())
        );
        System.out.println("rows=" + rows);
    }

}
