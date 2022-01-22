package com.springboot.claude.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.claude.models.Category;
import com.springboot.claude.models.Product;
import com.springboot.claude.models.ProductCategory;
import com.springboot.claude.services.ProductCategoryService;

@Controller
public class ProductsController {
	
	private final ProductCategoryService productCategoryService;
	public ProductsController(ProductCategoryService prodCatService) {
		
		this.productCategoryService = prodCatService;
	}
	
	// GET request to present form to add new product
	@GetMapping("/product/new")
	public String newProduct(@ModelAttribute("product") Product product, Model model) {
		return "newProduct.jsp";
	}
	
	// POST request for adding new product to database
	@PostMapping("/addProduct")
	public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/product/new";
		}
		else
		{
			productCategoryService.createProduct(product);
			return "redirect:/";
		}
	}
	
	// GET request fork showing detailed information on a product
	@GetMapping("/products/{id}")
	public String showProduct(@PathVariable("id") Long id, @ModelAttribute("product") ProductCategory productCategory, Model model) {
		Product selectedProduct = productCategoryService.findProductById(id);
		List<Category> categoriesListed = selectedProduct.getCategories();
		List<Category> categoriesNotListed = productCategoryService.findCategoriesNotInProducts(selectedProduct);
		
		model.addAttribute("product",selectedProduct);
		model.addAttribute("categoriesListed",categoriesListed);
		model.addAttribute("categoriesNotListed",categoriesNotListed);
		
		return "showProduct.jsp";
		
	}
	
	// POST request to adding category to product
	@PostMapping("/products/addCategory")
	public String addCategoryToProduct(@ModelAttribute("productCategory") ProductCategory productCategory, BindingResult result) {
		if(result.hasErrors()) {
			return "showProduct.jsp";
		}
		else {
			productCategoryService.createProductCategory(productCategory);
			Long id = productCategory.getCategory().getId();
			return "redirect:/products/"+id;
		}
	}
	// POST method to remove category from product
	@PostMapping("/products/removeProduct")
	public String removeProductCategory(@RequestParam(value="product") Product product, @RequestParam(value="category") Category category) {
		Long idProductCategoryToRemove;
		List<ProductCategory> allProductCategories = productCategoryService.findAllProductCategories();
		for(ProductCategory relationship : allProductCategories) {
			if(relationship.getProduct().getId()==product.getId() && relationship.getCategory().getId() == category.getId()) {
				idProductCategoryToRemove = relationship.getId();
				productCategoryService.deleteProductCategory(idProductCategoryToRemove);
			}
		}
		Long id = category.getId();
		return "redirect:/products/"+id; 
	}
	// GET request for deleting an item from database
	@GetMapping("/products/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		this.productCategoryService.deleteProduct(id);
		return "redirect:/";
	}
	 
}
