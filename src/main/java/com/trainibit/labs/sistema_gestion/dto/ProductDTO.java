package com.trainibit.labs.sistema_gestion.dto;

import com.trainibit.labs.sistema_gestion.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private double price;
    private String sku;
    private int stock;
    private Long storeId;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.sku = product.getSku();
        this.storeId = product.getStore() != null ? product.getStore().getId() : null;
    }
}
