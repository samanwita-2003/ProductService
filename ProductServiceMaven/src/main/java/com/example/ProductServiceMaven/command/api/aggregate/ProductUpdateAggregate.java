package com.example.ProductServiceMaven.command.api.aggregate;

import com.example.ProductServiceMaven.command.api.commands.UpdateProductCommand;
import com.example.ProductServiceMaven.command.api.events.ProductUpdateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductUpdateAggregate {
    @AggregateIdentifier
    private String productUpdateId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductUpdateAggregate(UpdateProductCommand updateProductCommand) {
        ProductUpdateEvent productUpdateEvent = new ProductUpdateEvent();
        BeanUtils.copyProperties(updateProductCommand, productUpdateEvent);
        AggregateLifecycle.apply(productUpdateEvent);
    }

    public ProductUpdateAggregate() {
    }

    @EventSourcingHandler
    public void on(ProductUpdateEvent productUpdateEvent) {
        this.productUpdateId = productUpdateEvent.getProductUpdateId();
        this.name = productUpdateEvent.getName();
        this.price = productUpdateEvent.getPrice();
        this.quantity = productUpdateEvent.getQuantity();
    }
}
