package com.pdp.yourmeal.repository;

import com.pdp.yourmeal.entity.Order;
import com.pdp.yourmeal.enums.OrderStatus;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByStatus(OrderStatus status);

    List<Order> findAllByUserId(Long userId);

    Order findByUserIdAndStatus(@NonNull Long userId, @NonNull OrderStatus status);

}