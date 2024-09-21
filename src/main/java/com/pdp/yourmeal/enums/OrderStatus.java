package com.pdp.yourmeal.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Aliabbos Ashurov
 * @since 19/September/2024  20:20
 **/
@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    CREATED("CREATED"),
    ACCEPTED("ACCEPTED");
    private final String status;
    }
