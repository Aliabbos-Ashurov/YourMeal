package com.pdp.yourmeal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  11:27
 **/
@Getter
@AllArgsConstructor
public enum OrderType {

    PICKUP("PICKUP"),
    DELIVERY("DELIVERY"),
    DEFAULT("DEFAULT");

    private final String type;
}
