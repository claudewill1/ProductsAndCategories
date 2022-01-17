package com.springboot.claude.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.claude.models.Category;
import com.springboot.claude.models.Product;
import com.springboot.claude.repositories.CategoryRepository;
import com.springboot.claude.repositories.ProductRepository;

@Service
public class ProductCategoryService {
	
	private final CategoryRepository categoryRepo;
	private final ProductRepository productRepo;
	
	public ProductCategoryService(CategoryRepository categoryRepo, ProductRepository productRepo) {
		this.categoryRepo = categoryRepo;
		this.productRepo = productRepo;
	}
	
	// retrieve all categories
	public List<Category> getAllCategories(){
		return this.categoryRepo.findAll();
	}
	// retrieve all products
	public List<Product> getAllProducts(){
		return this.productRepo.findAll();
	}
	
	// create category
	public Category createCategory(Category category) {
		return this.categoryRepo.save(category);
	}
	
	// create product
	public Product createProduct(Product product) {
		return this.productRepo.save(product);
	}
	
	// get category by id
	public Category findCategoryById(Long id) {
		return this.categoryRepo.findById(id).orElse(null);
	}
	// Retrieves all products that are not categorized to a specific category
	public List<Product> findCategoriesNotInProducts(Category category) {
		return this.productRepo.findByCategoriesNotContains(category);
	}
	// Retrieves all categories that a specific product has not been categorized yet
	public List<Category> findProductsNotInCategory(Product product){
		return this.categoryRepo.findByProductsNotContains(product);
	}
	// get product by id
	public Product findProductById(Long id) {
		return this.productRepo.findById(id).orElse(null);
	}
	
	// delete category
	public void deleteCategory(Long id) {
		this.categoryRepo.deleteById(id);
	}
	
	// delete product
	public void deleteProduct(Long id) {
		this.productRepo.deleteById(id);
	}
}
