package com.pdp.yourmeal.dto.response;

import com.pdp.yourmeal.dto.DTO;
import com.pdp.yourmeal.entity.Order;
import lombok.NonNull;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  10:24
 **/
public record OrderDTO(
        @NonNull Long id,
        @NonNull Long userId,
        @NonNull List<OrderItemDTO> items,
        @NonNull Integer count,
        @NonNull Double total
) implements DTO {
    public static OrderDTO of(Order order, List<OrderItemDTO> items) {
        double sum = items.stream().mapToDouble(OrderItemDTO::price).sum();
        return new OrderDTO(order.getId(), order.getUser().getId(), items, items.size(), sum);
    }
}
