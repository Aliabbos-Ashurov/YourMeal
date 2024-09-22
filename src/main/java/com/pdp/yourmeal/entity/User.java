package com.pdp.yourmeal.entity;

import com.pdp.yourmeal.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 19/September/2024  19:44
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "users")
public class User extends Auditable {

    @Column(name = "fullname", nullable = false, length = 50)
    private String fullname;

    @Column(nullable = false, length = 50, unique = true)
    private String username;


    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "phone", length = 15, unique = true)
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
}
