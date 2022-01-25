package com.springboot.claude.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.claude.models.Category;
import com.springboot.claude.models.Product;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	// retrieves all categories from the database
    List<Category> findAll();
    // retrieves all categories that a specific product has not been categorized yet
    List<Category> findByProductsNotContains(Product product);
}
