package com.pdp.yourmeal.service;

import com.pdp.yourmeal.entity.OrderItem;
import com.pdp.yourmeal.handler.exception.ResourceNotFoundException;
import com.pdp.yourmeal.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  09:56
 **/
@Service
@RequiredArgsConstructor
public class OrderItemService implements BaseService<OrderItem, Long> {

    private final OrderItemRepository orderItemRepository;

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
