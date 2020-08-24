package com.example.demo.services;


import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void add(Product product) {
        productRepository.save(product);
    }
    public void delete(Long id) { productRepository.delete(id);}
    public void clear(){productRepository.clear();}
    public List<Product> findByTitle(String title){return productRepository.findByTitle (title);}
    public void SaveOrupdate(Product product){
        if (product.getId ().equals (null)){
            productRepository.save (product);
        }else{

        }

    }


}
