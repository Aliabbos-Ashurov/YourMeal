package com.pdp.yourmeal.service;

import com.pdp.yourmeal.dto.CreateOrderDTO;
import com.pdp.yourmeal.dto.OrderDTO;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  10:05
 **/
public interface OrderService {

    OrderDTO getOrCreate(CreateOrderDTO createOrderDTO);
}
