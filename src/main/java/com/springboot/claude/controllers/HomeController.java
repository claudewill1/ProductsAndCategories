package com.springboot.claude.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.claude.models.Category;
import com.springboot.claude.models.Product;
import com.springboot.claude.services.ProductCategoryService;

@Controller
public class HomeController {
	private final ProductCategoryService productCategoryService;
	
	public HomeController(ProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}
	// GET request for loading index page
	@GetMapping("/")
	public String index(Model model) {
		List<Category> categories = productCategoryService.getAllCategories();
		List<Product> products = productCategoryService.getAllProducts();
		
		model.addAttribute("categories",categories);
		model.addAttribute("products",products);
		return "index.jsp";
	}
	
	
}
