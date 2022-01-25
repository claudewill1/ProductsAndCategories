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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.claude.models.Category;
import com.springboot.claude.models.Product;
import com.springboot.claude.models.ProductCategory;
import com.springboot.claude.services.ProductCategoryService;

@Controller
public class CategoriesController {
	private final ProductCategoryService productCategoryService;
	
	public CategoriesController(ProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}
	
	// Category Requests
	
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
	
	// GET request for showing information of a category in detail
	@RequestMapping("/categories/{id}")
	public String showCategory(@PathVariable("id") Long id, @ModelAttribute("productCategory") ProductCategory productCategory, Model model) {
		Category categorySelected = productCategoryService.findCategoryById(id);
		List <Product> productsListed = categorySelected.getProducts();
		List <Product> productsNotListed = productCategoryService.findProductsNotInCategory(categorySelected);
		
		model.addAttribute("category", categorySelected);
		model.addAttribute("productsListed",productsListed);
		model.addAttribute("productsNotListed",productsNotListed);
		return "showCategory.jsp";
	}
	
	// POST request to add product to category
	@RequestMapping(value="/products/addCategory", method=RequestMethod.POST)
	public String addCategoryToProduct(@ModelAttribute("productCategory") ProductCategory productCategory, BindingResult result) {
		
		if(result.hasErrors()) {
			return "showProduct.jsp";
		}
		else {
			productCategoryService.createProductCategory(productCategory);
			Long id = productCategory.getProduct().getId();
			return "redirect:/";
		}
				
	}
	// POST method to remove category from product
	@PostMapping("/categories/removeProduct")
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
		return "redirect:/categories/"+id; 
	}
	// GET request for deleting a category from database
		@GetMapping("/category/delete/{id}")
		public String deleteCategory(@PathVariable("id") Long id) {
			this.productCategoryService.deleteCategory(id);
			return "redirect:/";
		}
}
