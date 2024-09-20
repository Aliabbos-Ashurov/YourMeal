package com.pdp.yourmeal.repository;

import com.pdp.yourmeal.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}