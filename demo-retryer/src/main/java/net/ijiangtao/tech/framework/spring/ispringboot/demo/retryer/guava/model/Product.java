package net.ijiangtao.tech.framework.spring.ispringboot.demo.retryer.guava.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品model
 */
@Data
@AllArgsConstructor
public class Product {

    private Long id;

    private String name;

    private Integer count;

    private BigDecimal price;

}
