package com.pdp.yourmeal.service;

import com.pdp.yourmeal.entity.Order;
import com.pdp.yourmeal.entity.OrderItem;
import com.pdp.yourmeal.entity.Product;
import com.pdp.yourmeal.handler.exception.ResourceNotFoundException;
import com.pdp.yourmeal.repository.OrderItemRepository;
import com.pdp.yourmeal.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  09:56
 **/
@Service
@RequiredArgsConstructor
public class OrderItemService implements BaseService<OrderItem, Long> {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;


    public OrderItem getOrCreate(Order order, Product product, int quantity) {
        Optional<OrderItem> existingOrderItem = order.getOrderItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingOrderItem.isPresent()) {
            OrderItem orderItem = existingOrderItem.get();
            orderItem.setQuantity(orderItem.getQuantity() + quantity);
            orderItem.setPrice(orderItem.getQuantity() * product.getPrice());
            return orderItemRepository.save(orderItem);
        } else {
            OrderItem newOrderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(quantity)
                    .price(quantity * product.getPrice())
                    .build();
            order.getOrderItems().add(newOrderItem);
            return orderItemRepository.save(newOrderItem);
        }
    }

    @Override
    public OrderItem save(OrderItem entity) {
        return orderItemRepository.save(entity);
    }

    @Override
    public OrderItem findById(Long id) {
        return orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order item not found by id: {0}", id));
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return orderItemRepository.existsById(id);
    }
}
