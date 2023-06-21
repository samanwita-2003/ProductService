package com.example.ProductServiceMaven.command.api.aggregate;

import com.example.ProductServiceMaven.command.api.commands.CreateProductCommand;
import com.example.ProductServiceMaven.command.api.commands.UpdateProductCommand;
import com.example.ProductServiceMaven.command.api.events.ProductCreatedEvent;
import com.example.ProductServiceMaven.command.api.events.ProductUpdateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        // performing all the validations here
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
        // to publish event
        AggregateLifecycle.apply(productCreatedEvent);

    }


    public ProductAggregate() {
    }

    //event sourcing handler to update aggregate values after event
    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.quantity = productCreatedEvent.getQuantity();
        this.productId = productCreatedEvent.getProductId();
        this.name = productCreatedEvent.getName();
        this.price = productCreatedEvent.getPrice();
    }
}
