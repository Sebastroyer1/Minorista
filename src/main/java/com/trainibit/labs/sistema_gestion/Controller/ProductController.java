package com.trainibit.labs.sistema_gestion.Controller;


import com.trainibit.labs.sistema_gestion.Repository.ProductRepository;
import com.trainibit.labs.sistema_gestion.dto.ProductDTO;
import com.trainibit.labs.sistema_gestion.model.Inventory;
import com.trainibit.labs.sistema_gestion.model.Product;
import com.trainibit.labs.sistema_gestion.model.Store;
import com.trainibit.labs.sistema_gestion.service.InventoryService;
import com.trainibit.labs.sistema_gestion.service.ProductService;
import com.trainibit.labs.sistema_gestion.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

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
                .map(product -> {
                    Inventory inventory = inventoryService.getInventoryByProductId(product.getId());
                    return new ProductDTO(product, inventory);
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO dto) {
        // 1. Crear y guardar el producto
        Product product = Product.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .sku(dto.getSku())
                .build();
        product = productService.saveProduct(product);

        // 2. Buscar la tienda
        Optional<Store> storeOptional = storeService.getStores().stream()
                .filter(s -> s.getId() == dto.getStoreId())
                .findFirst();

        if (storeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tienda no encontrada.");
        }

        // 3. Guardar en inventario
        Inventory inventory = Inventory.builder()
                .product(product)
                .store(storeOptional.get())
                .quantity(dto.getStock())
                .build();
        inventoryService.saveInventory(inventory);

        return ResponseEntity.status(HttpStatus.CREATED).body("Producto guardado correctamente.");

    }

    @GetMapping("/category/{category}")
    public List<ProductDTO> getProductsByCategory(@PathVariable String category) {
        List<Product> productos = productService.getProductsByCategory(category);

        return productos.stream()
                .map(product -> {
                    Inventory inventory = inventoryService.getInventoryByProductId(product.getId());
                    return new ProductDTO(product, inventory);
                })
                .collect(Collectors.toList());
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProductDTO dto) {
        // Paso 1: Buscar y actualizar el producto
        Product existingProduct = productService.getProductById(dto.getId());
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }

        existingProduct.setName(dto.getName());
        existingProduct.setCategory(dto.getCategory());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setSku(dto.getSku());

        Product updatedProduct = productService.saveProduct(existingProduct); // o updateProduct

        // Paso 2: Buscar y actualizar el inventario
        Inventory inventory = inventoryService.getInventoryByProductId(dto.getId());

        if (inventory == null) {
            // Si no existe el inventario, lo creamos
            inventory = new Inventory();
            inventory.setProduct(existingProduct);
        }

        inventory.setQuantity(dto.getStock());
        inventory.setStore(storeService.getStoreById(dto.getStoreId())); // debe existir este método
        inventoryService.saveInventory(inventory);

        return ResponseEntity.ok().body(dto);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Inventory inventory = inventoryService.getInventoryByProductId(id);
        if (inventory != null) {
            inventoryService.deleteInventoryById(inventory.getId());// elimina el inventario asociado
        }
        productService.deleteProduct(id);// Luego, elimina el producto

        return ResponseEntity.noContent().build(); // 204
    }
}
