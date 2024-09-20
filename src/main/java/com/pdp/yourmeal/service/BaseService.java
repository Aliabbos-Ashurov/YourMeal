package com.pdp.yourmeal.service;

import com.pdp.yourmeal.entity.Auditable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  08:43
 **/
@Service
public interface BaseService<T extends Auditable, ID extends Serializable> {

    T save(T entity);

    T findById(ID id);

    List<T> findAll();

    void deleteById(ID id);

    boolean existsById(ID id);
}
