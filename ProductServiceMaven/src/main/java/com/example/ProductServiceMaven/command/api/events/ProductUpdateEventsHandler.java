package com.example.ProductServiceMaven.command.api.events;

import com.example.ProductServiceMaven.command.api.data.Product;
import com.example.ProductServiceMaven.command.api.data.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductUpdateEventsHandler {
    private ProductRepository productRepository;

    public ProductUpdateEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductUpdateEvent productUpdateEvent) {
        Optional<Product> optionalProduct = productRepository.findById(productUpdateEvent.getProductUpdateId());
        if(optionalProduct.isPresent())
        {
            Product product = new Product();
            BeanUtils.copyProperties(productUpdateEvent, product);
            productRepository.save(product);
        }
    }

}
