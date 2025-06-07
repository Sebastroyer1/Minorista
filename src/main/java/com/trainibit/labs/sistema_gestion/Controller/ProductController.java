package com.trainibit.labs.sistema_gestion.Controller;


import com.trainibit.labs.sistema_gestion.Repository.ProductRepository;
import com.trainibit.labs.sistema_gestion.dto.ProductDTO;
import com.trainibit.labs.sistema_gestion.model.Product;
import com.trainibit.labs.sistema_gestion.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @Autowired
    private ProductRepository productRepository;


   /* @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }*/
    @GetMapping
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/category/{category}")
    public List<ProductDTO> getProductsByCategory(@PathVariable String category) {
        List<Product> productos = productService.getProductsByCategory(category);
        return productos.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/store/{storeId}")
    public List<Product> getByStore(@PathVariable Long storeId) {
        return productService.getProductsByStore(storeId);
    }

    @PutMapping("/update")
    public Product update(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
