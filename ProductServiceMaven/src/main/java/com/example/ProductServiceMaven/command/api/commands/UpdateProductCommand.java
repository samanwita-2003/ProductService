package com.example.ProductServiceMaven.command.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class UpdateProductCommand {
    @TargetAggregateIdentifier
    private String productUpdateId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
