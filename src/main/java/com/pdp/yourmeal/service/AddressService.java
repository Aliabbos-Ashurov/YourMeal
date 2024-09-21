package com.pdp.yourmeal.service;

import com.pdp.yourmeal.entity.Address;
import com.pdp.yourmeal.repository.AddressRepository;
import com.pdp.yourmeal.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  11:21
 **/
@Service
@RequiredArgsConstructor
public class AddressService implements BaseService<Address, Long> {

    private final AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return addressRepository.existsById(id);
    }
}
