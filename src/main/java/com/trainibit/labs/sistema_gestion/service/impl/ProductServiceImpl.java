package com.trainibit.labs.sistema_gestion.service.impl;

import com.trainibit.labs.sistema_gestion.Repository.StoreRepository;
import com.trainibit.labs.sistema_gestion.model.Product;
import com.trainibit.labs.sistema_gestion.model.Store;
import com.trainibit.labs.sistema_gestion.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trainibit.labs.sistema_gestion.Repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        System.out.println("Buscando productos por categoría: " + category);
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateProduct(Product updatedProduct) {
        Product existingProduct = productRepository.findById(updatedProduct.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + updatedProduct.getId()));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setSku(updatedProduct.getSku());

        if (updatedProduct.getStore() != null && updatedProduct.getStore().getId() > 0) {
            Store store = storeRepository.findById(updatedProduct.getStore().getId())
                    .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));
            existingProduct.setStore(store);
        }

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByStore(Long storeId) {
        return productRepository.findByStoreId(storeId);
    }

}
