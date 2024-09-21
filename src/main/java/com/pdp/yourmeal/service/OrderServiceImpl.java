package com.pdp.yourmeal.service;

import com.pdp.yourmeal.dto.*;
import com.pdp.yourmeal.entity.Order;
import com.pdp.yourmeal.entity.OrderItem;
import com.pdp.yourmeal.entity.Product;
import com.pdp.yourmeal.enums.OrderStatus;
import com.pdp.yourmeal.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.collect;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  11:23
 **/
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderItemService orderItemService;
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    public OrderDTO getOrCreate(CreateOrderDTO dto) {
        Order order = orderRepository.findByUserIdAndStatus(dto.userId(), OrderStatus.CREATED);
        if (Objects.isNull(order)) {
            Order build = Order.builder()
                    .user(userService.findByIdUser(dto.userId()))
                    .build();
            order = orderRepository.save(build);
        }
        Product product = productService.findByIdProduct(dto.productId());
        OrderItem orderItem = orderItemService.getOrCreate(order, product, dto.quantity());
        double totalAmount = order.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
        List<OrderItemDTO> itemDTOs = order.getOrderItems().stream()
                .map(orderI -> OrderItemDTO.of(orderI.getProduct(), orderI.getQuantity()))
                .collect(Collectors.toList());
        return OrderDTO.of(order, itemDTOs);
    }

    public boolean confirmOrder(ConfirmOrderDTO dto) {
        Optional<Order> order = orderRepository.findById(dto.orderId());
        if (order.isPresent()) {
            order.get().setStatus(OrderStatus.ACCEPTED);
            orderRepository.save(order.get());
            return true;
        }
        return false;
    }
}
