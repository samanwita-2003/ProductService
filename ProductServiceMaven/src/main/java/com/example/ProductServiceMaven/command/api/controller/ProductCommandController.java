package com.example.ProductServiceMaven.command.api.controller;

import com.example.ProductServiceMaven.command.api.commands.CreateProductCommand;
import com.example.ProductServiceMaven.command.api.commands.UpdateProductCommand;
import com.example.ProductServiceMaven.command.api.data.Product;
import com.example.ProductServiceMaven.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }



    @PostMapping
    public String addProduct(@RequestBody ProductRestModel productRestModel)
    {
        CreateProductCommand createProductCommand =
                CreateProductCommand.builder()
                        .productId(UUID.randomUUID().toString())
                        .name(productRestModel.getName())
                        .price(productRestModel.getPrice())
                        .quantity(productRestModel.getQuantity())
                        .build();
        String result = commandGateway.sendAndWait(createProductCommand);
        return result;
    }

//    ------------------------Modified part---------------------------------------------
    @PutMapping
    public String updateProduct(@RequestBody Product product)
    {
        UpdateProductCommand updateProductCommand =
                UpdateProductCommand.builder()
                        .productUpdateId(product.getProductId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .build();
        String result = commandGateway.sendAndWait(updateProductCommand);
        return result;
    }

}
