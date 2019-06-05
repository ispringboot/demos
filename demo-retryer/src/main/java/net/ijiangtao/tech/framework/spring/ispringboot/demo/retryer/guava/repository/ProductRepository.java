package net.ijiangtao.tech.demo.retryer.guava.repository;

import net.ijiangtao.tech.demo.retryer.guava.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 商品DAO
 */
@Repository
public class ProductRepository {

    private static ConcurrentHashMap<Long,Product> products=new  ConcurrentHashMap();

    private static AtomicLong ids=new AtomicLong(0);

    public List<Product> findAll(){
        return new ArrayList<>(products.values());
    }

    public Product findById(Long id){
        return products.get(id);
    }

    public Product updatePrice(Long id, BigDecimal price){
        Product p=products.get(id);
        if (null==p){
            return p;
        }
        p.setPrice(price);
        return p;
    }

    public Product addProduct(Product product){
        Long id=ids.addAndGet(1);
        product.setId(id);
        products.put(id,product);
        return product;
    }

}
