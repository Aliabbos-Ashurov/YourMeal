package com.pdp.yourmeal.dto;

import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  10:37
 **/
public record OrderItemDTO(
        @NonNull ProductDTO product,
        @NonNull Integer quantity,
        double price
) implements DTO {
    public static OrderItemDTO of(ProductDTO product, int quantity) {
        return new OrderItemDTO(product, quantity, product.price() * quantity);
    }
}
