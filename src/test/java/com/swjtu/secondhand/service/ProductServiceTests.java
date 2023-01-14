package com.swjtu.secondhand.service;

import com.swjtu.secondhand.entity.Product;
import com.swjtu.secondhand.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductServiceTests {
    @Autowired
    private IProductService productService;

    @Test
    public void getProductList() {
        try {
            List<Product> list = productService.getProductList();
            System.out.println("count=" + list.size());
            for (Product item : list) {
                System.out.println(item);
            }
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getProductListByCategory() {
        try {
            List<Product> list = productService.getProductListByCategory("00008");
            System.out.println("count=" + list.size());
            for (Product item : list) {
                System.out.println(item);
            }
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getProductDetailById() {
        try {
            Product product = productService.getProductDetailById("00000000000000000004");
            System.out.print(product);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addProduct() {
        try {
            Product product = new Product();
//            product.setId("00000000000000000007");
//            product.setSeller("0000000003");
            product.setName("布洛芬胶囊");
            product.setCategory("00020");
            product.setDescription("abyigurea");
            product.setLocation("1");
            product.setPrice(3.0f);
            product.setTags("布洛芬");
            productService.addProduct("000000000",product);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeProductInfo() {
        try {
            Product product = new Product();
//            product.setId("00000000000000000007");
//            product.setSeller("0000000003");
            product.setName("布洛芬");
            product.setCategory("00020");
            product.setDescription("按颗卖");
//            product.setLocation("1");
            product.setPrice(4.0f);
//            product.setTags("布洛芬");
            productService.changeProductInfo("00000000000000000007",product);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void changeProductImg() {
        try {
            productService.changeProductImg("00000000000000000009","/");
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
