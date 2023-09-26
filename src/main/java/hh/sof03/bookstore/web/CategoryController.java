package hh.sof03.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.sof03.bookstore.domain.Category;
import hh.sof03.bookstore.domain.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }

    // Add a new category
    @RequestMapping("/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    // Save new category
    @PostMapping("/savecategory")
    public String saveCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:categories";
    }

    // Delete a category
    @GetMapping("/deletecategory/{id}")
    public String deleteCategory(@PathVariable("id") Long categoryId, Model model) {
        categoryRepository.deleteById(categoryId);
        return "redirect:../categories";
    }

    // Edit category
    @RequestMapping("/editcategory/{id}")
    public String editCategory(@PathVariable("id") Long categoryId, Model model) {
        model.addAttribute("category", categoryRepository.findById(categoryId));
        return "editcategory";
    }

}
