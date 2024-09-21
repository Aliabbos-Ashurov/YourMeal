package com.pdp.yourmeal.controller;

import com.pdp.yourmeal.dto.request.ConfirmOrderDTO;
import com.pdp.yourmeal.dto.request.CreateOrderDTO;
import com.pdp.yourmeal.dto.response.OrderDTO;
import com.pdp.yourmeal.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  16:44
 **/
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    @Qualifier("OrderServiceImpl")
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderDTO dto) {
        return ResponseEntity.ok(orderService.getOrCreate(dto));
    }

    @PostMapping("/confirm")
    public ResponseEntity<Void> confirmOrder(@RequestBody ConfirmOrderDTO dto) {
        orderService.confirmOrder(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/get/{user-id}")
    public ResponseEntity<OrderDTO> getUserOrder(@PathVariable(name = "user-id") Long id) {
        return ResponseEntity.ok(orderService.getUserOrder(id));
    }

}
