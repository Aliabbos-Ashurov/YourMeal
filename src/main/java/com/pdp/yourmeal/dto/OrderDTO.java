package com.pdp.yourmeal.dto;

import com.pdp.yourmeal.entity.Order;
import com.pdp.yourmeal.entity.OrderItem;
import com.pdp.yourmeal.enums.OrderStatus;
import lombok.NonNull;

import java.util.List;
import java.util.stream.DoubleStream;

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
        double totalAmount = 0;
        double sum = items.stream().mapToDouble(item -> item.price() + totalAmount).sum();
        return new OrderDTO(order.getId(), order.getUser().getId(), items, items.size(), sum);
    }
}
