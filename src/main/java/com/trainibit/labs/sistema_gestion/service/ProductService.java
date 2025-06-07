package com.trainibit.labs.sistema_gestion.service;

import com.trainibit.labs.sistema_gestion.model.Product;

import java.util.List;

public interface ProductService{
    List<Product> getAllProducts();
    Product saveProduct(Product product);
    List<Product> getProductsByCategory(String category);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    List<Product> getProductsByStore(Long storeId);
}
