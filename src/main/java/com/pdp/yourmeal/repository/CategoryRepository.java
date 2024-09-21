package com.pdp.yourmeal.repository;

import com.pdp.yourmeal.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitleContainsIgnoreCase(String title);
}