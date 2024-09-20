package com.pdp.yourmeal.service;

import com.pdp.yourmeal.dto.DTO;
import com.pdp.yourmeal.entity.Auditable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 19/September/2024  20:57
 **/
@Service
public interface BaseDtoService<T extends Auditable, ID extends Serializable, D extends DTO> {

    D save(D dto);

    D findById(ID id);

    List<D> findAll();

    void deleteById(ID id);

    boolean existsById(ID id);
}