package com.pdp.yourmeal.service;

import com.pdp.yourmeal.dto.request.AddressDTO;
import com.pdp.yourmeal.dto.request.ConfirmOrderDTO;
import com.pdp.yourmeal.dto.request.CreateOrderDTO;
import com.pdp.yourmeal.dto.response.OrderDTO;
import com.pdp.yourmeal.dto.response.OrderItemDTO;
import com.pdp.yourmeal.dto.response.ProductDTO;
import com.pdp.yourmeal.entity.Address;
import com.pdp.yourmeal.entity.Order;
import com.pdp.yourmeal.entity.OrderItem;
import com.pdp.yourmeal.entity.Product;
import com.pdp.yourmeal.enums.OrderStatus;
import com.pdp.yourmeal.enums.OrderType;
import com.pdp.yourmeal.handler.exception.OrderNotFoundException;
import com.pdp.yourmeal.mapper.ProductMapper;
import com.pdp.yourmeal.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  11:23
 **/
@Service("OrderServiceImpl")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderItemService orderItemService;
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final AddressService addressService;

    @Override
    @Transactional
    public OrderDTO getUserOrder(Long userId) {
        Order order = orderRepository.findByUserIdAndStatus(userId, OrderStatus.CREATED);
        if (Objects.isNull(order)) throw new OrderNotFoundException("Order not found with user-id: {0}", userId);
        List<OrderItemDTO> list = order.getOrderItems().stream()
                .map(orderItem -> {
                    ProductDTO productDTO = productMapper.toProductDTO(orderItem.getProduct());
                    return OrderItemDTO.of(productDTO, orderItem.getQuantity());
                })
                .collect(Collectors.toList());
        return OrderDTO.of(order, list);
    }

    @Override
    @Transactional
    public OrderDTO getOrCreate(CreateOrderDTO dto) {
        Order order = orderRepository.findByUserIdAndStatus(dto.userId(), OrderStatus.CREATED);
        if (Objects.isNull(order)) {
            Order build = Order.builder()
                    .user(userService.findByIdUser(dto.userId()))
                    .orderItems(new ArrayList<>())
                    .build();
            order = orderRepository.save(build);
        }
        Product product = productService.findByIdProduct(dto.productId());
        orderItemService.getOrCreate(order, product, dto.quantity());
        double totalAmount = order.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);;
        orderRepository.save(order);
        List<OrderItemDTO> itemDTOs = order.getOrderItems().stream()
                .map(orderI -> OrderItemDTO.of(productMapper.toProductDTO(orderI.getProduct()), orderI.getQuantity()))
                .collect(Collectors.toList());
        return OrderDTO.of(order, itemDTOs);
    }

    @Override
    public boolean confirmOrder(ConfirmOrderDTO dto) {
        Order order = orderRepository.findById(dto.orderId())
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found With Id: " + dto.orderId()));
        order.setStatus(OrderStatus.ACCEPTED);
        order.setType(dto.type());
        order.setReceiverName(dto.fullname());
        order.setReceiverPhone(dto.phone());
        if (dto.address() != null || dto.type().equals(OrderType.DELIVERY)) {
            assert dto.address() != null;
            Address address = buildAddressFromDTO(dto.address());
            addressService.save(address);
            order.setDeliveryAddress(address);
        }
        orderRepository.save(order);
        return true;
    }

    private Address buildAddressFromDTO(AddressDTO dtoAddress) {
        return Address.builder()
                .street(dtoAddress.street())
                .apartmentNumber(dtoAddress.apartmentNumber())
                .buildingNumber(dtoAddress.buildingNumber())
                .intercom(dtoAddress.intercom())
                .build();
    }
}
