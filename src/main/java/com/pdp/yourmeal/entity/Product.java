package com.pdp.yourmeal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 19/September/2024  19:48
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
public class Product extends Auditable {

    @Column(name = "image")
    private String image;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "weight")
    private double weight;

    @Column(name = "calories")
    private double calories;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @Column(name = "compound")
    private List<String> compound;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
