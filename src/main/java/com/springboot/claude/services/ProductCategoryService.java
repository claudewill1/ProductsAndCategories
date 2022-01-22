package com.springboot.claude.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.claude.models.Category;
import com.springboot.claude.models.Product;
import com.springboot.claude.models.ProductCategory;
import com.springboot.claude.repositories.CategoryRepository;
import com.springboot.claude.repositories.ProductCategoryRepository;
import com.springboot.claude.repositories.ProductRepository;

@Service
public class ProductCategoryService {
	
	private final CategoryRepository categoryRepo;
	private final ProductRepository productRepo;
	private final ProductCategoryRepository productCategoryRepo;
	
	public ProductCategoryService(CategoryRepository categoryRepo, ProductRepository productRepo, ProductCategoryRepository productCategoryRepo) {
		this.categoryRepo = categoryRepo;
		this.productRepo = productRepo;
		this.productCategoryRepo = productCategoryRepo;
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
	// create productCategory
	public ProductCategory createProductCategory(ProductCategory productCategory) {
		return this.productCategoryRepo.save(productCategory);
	}
	// Retrieves all relationships between products and categories
	public List<ProductCategory> findAllProductCategories(){
		return this.productCategoryRepo.findAll();
	}
	// get category by id
	public Category findCategoryById(Long id) {
		return this.categoryRepo.findById(id).orElse(null);
	}
	// Retrieves all products that are not categorized to a specific category
	public List<Category> findCategoriesNotInProducts(Product product) {
		
		return this.categoryRepo.findByProductsNotContains(product);
	}
	// Retrieves all categories that a specific product has not been categorized yet
	public List<Product> findProductsNotInCategory(Category category){
		return this.productRepo.findByCategoriesNotContains(category);
	}
	// Add product to category
	public void addProductToCategory(Product product,Category category) {
		// get Product list from the category
		List<Product> products = category.getProducts();
		products.add(product);
		this.categoryRepo.save(category);
	    
	}
	// Add category to product
	public void addCategoryToProduct(Category category,Product product) {
		// get category list from the product
		List<Category> categories = product.getCategories();
		categories.add(category);
		// Update DB
		this.productRepo.save(product);
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
	// delete productCategory
	public void deleteProductCategory(Long id) {
		this.productCategoryRepo.deleteById(id);
	}
}
