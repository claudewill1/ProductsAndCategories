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

import com.springboot.claude.models.Category;
import com.springboot.claude.models.Product;
import com.springboot.claude.services.ProductCategoryService;

@Controller
public class CategoriesController {
	private final ProductCategoryService productCategoryService;
	
	public CategoriesController(ProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}
	// GET request to present form to add a new category
	@GetMapping("/category/new")
	public String newCategory(@ModelAttribute("category") Category category, Model model) {
		return "newCategory.jsp";
	}
	// POST request for adding a new category to database
	@PostMapping("/addCategory")
	public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
		if(result.hasErrors()) {
			return "newCategory.jsp";
		} else
		{
			productCategoryService.createCategory(category);
			return "redirect:/";
		}
	}
	// GET request for deleting a category from database
	@GetMapping("/category/delete/{id}")
	public String deleteCategory(@PathVariable("id") Long id) {
		this.productCategoryService.deleteCategory(id);
		return "redirect:/";
	}
	// GET request for showing information of a category in detail
	@GetMapping("/category/{id}")
	public String showCategory(@PathVariable("id") Long id,@ModelAttribute("category") Category category, Model model) {
		Category selectedCategory = productCategoryService.findCategoryById(id);
		List<Product> listedProducts = this.productCategoryService.getAllProducts();
		List<Product> productsNotListed = this.productCategoryService.findCategoriesNotInProducts(selectedCategory);
		
		model.addAttribute("selectedCategory",selectedCategory);
		model.addAttribute("listedProducts",listedProducts);
		model.addAttribute("productsNotListed",productsNotListed);
		
		return "showCategory.jsp";
		
	}
}
