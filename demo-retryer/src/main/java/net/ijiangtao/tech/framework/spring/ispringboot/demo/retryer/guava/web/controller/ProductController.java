package net.ijiangtao.tech.demo.retryer.guava.web.controller;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.demo.retryer.guava.model.Product;
import net.ijiangtao.tech.demo.retryer.guava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductRepository pRepo;

    /**
     * 通过如下请求添加商品
     *
     * http://localhost:8304/product?name=product_name&count=10
     *
     * @param name
     * @param count
     * @return
     */
    @GetMapping("/product")
    public List<Product> add(String name,Integer count){

        if((!Strings.isNullOrEmpty(name))&&null!=count ){

            Product product=new Product(null,name,count,null);

            Product pAdded=pRepo.addProduct(product);
            log.info("pAdded id={}",pAdded.getId());

        }

        return pRepo.findAll();

    }


}
