package ru.geekbrains.spring.context.app.meta;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Eggs", 70),
                new Product(2L, "Milk", 50),
                new Product(3L, "Chocolate", 60),
                new Product(4L, "Potatoes", 30),
                new Product(5L, "Beer", 80)
        ));
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public Product getProduct(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

}
