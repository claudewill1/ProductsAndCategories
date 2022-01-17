package com.springboot.claude.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.claude.models.Category;
import com.springboot.claude.models.Product;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	// retrieve all categories from database
	List<Category> findAll();
	
	//
	List<Category> findAllByProducts(Product product);
	// retrieves all categories that a specific product has not be categorized yet
	List<Category> findByProductsNotContains(Product product);
}
