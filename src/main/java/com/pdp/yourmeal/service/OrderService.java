package com.pdp.yourmeal.service;

import com.pdp.yourmeal.dto.request.ConfirmOrderDTO;
import com.pdp.yourmeal.dto.request.CreateOrderDTO;
import com.pdp.yourmeal.dto.response.OrderDTO;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  10:05
 **/
public interface OrderService {

    OrderDTO getOrCreate(CreateOrderDTO createOrderDTO);

    boolean confirmOrder(ConfirmOrderDTO dto);

    OrderDTO getUserOrder(Long userId);
}
