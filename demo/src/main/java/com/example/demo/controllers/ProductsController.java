package com.example.demo.controllers;


import com.example.demo.entities.Product;
import com.example.demo.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }


    @GetMapping
    public String showProductsList(Model model) {
        Product product = new Product();
        String title = "Bread";
        System.out.println (productsService.findByTitle (title).get (0).getTitle () );

        if (title.equals (null)) {
            model.addAttribute("products", productsService.getAllProducts());
        }else{
            model.addAttribute("products", productsService.findByTitle (title));
        }

        model.addAttribute("product", product);
        return "products";
    }

    @PostMapping("/edit")

    @GetMapping("/edit/{id}")
    public String showEditProductsForm(Model model, @PathVariable(value = "id") Long id) {
        Product product=productsService.getById (id);
        model.addAttribute ("product",product);
        return "product-edit";
    }
    @GetMapping("/add")
    public String showAddProductsForm(Model model) {
        Product product=new Product();
        model.addAttribute ("product",product);
        return "product-edit";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product")Product product) {
        productsService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String clearProduct() {
        productsService.clear ();
        return "redirect:/products";
    }

    @GetMapping("/del/{id}")
    public String delOneProduct(Model model, @PathVariable(value = "id") Long id) {
        productsService.delete (id);
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        System.out.println(product.getTitle());
        model.addAttribute("product", product);
        return "product-page";
    }
}
