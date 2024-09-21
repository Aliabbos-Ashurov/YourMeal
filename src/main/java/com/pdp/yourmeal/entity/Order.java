package com.pdp.yourmeal.entity;

import com.pdp.yourmeal.enums.OrderStatus;
import com.pdp.yourmeal.enums.OrderType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 19/September/2024  19:56
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "orders")
@Entity
public class Order extends Auditable {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address deliveryAddress;

    @Builder.Default
    @Column(name = "status", nullable = false, length = 30)
    private OrderStatus status = OrderStatus.CREATED;

    @Column(name = "type", nullable = false, length = 30)
    private OrderType type;

    @Builder.Default
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount = 0.0D;

    private String receiverName;
    private String receiverPhone;
}
