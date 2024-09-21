package com.pdp.yourmeal.repository;

import com.pdp.yourmeal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.compound WHERE p.category.title = ?1")
    List<Product> findAllByCategoryTitle(String title);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.compound")
    List<Product> findAllWithCompound();

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.compound WHERE p.id = ?1")
    Product findById(long id);
}