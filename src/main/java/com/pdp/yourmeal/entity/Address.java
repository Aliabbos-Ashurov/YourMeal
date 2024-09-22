package com.pdp.yourmeal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Aliabbos Ashurov
 * @since 19/September/2024  19:57
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
public class Address extends Auditable{

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "apartment_number", length = 10)
    private String apartmentNumber;

    @Column(name = "building_number", length = 10)
    private String buildingNumber;

    @Column(name = "intercom", length = 10)
    private String intercom;
}
