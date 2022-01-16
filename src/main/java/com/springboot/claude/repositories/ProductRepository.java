package com.springboot.claude.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.claude.models.Category;
import com.springboot.claude.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	// get all products
	List<Product> findAll();
	// retrieve all products that aren ot categorized
	List<Product> findByCategoriesNotContains(Category category);
}
