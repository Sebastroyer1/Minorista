package com.trainibit.labs.sistema_gestion.dto;

import com.trainibit.labs.sistema_gestion.model.Inventory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryDTO {
    private Long id; // id del inventario
    private Long storeId;
    private ProductyDTO product;

    public InventoryDTO(Inventory inventory) {
        this.id = inventory.getId();
        this.storeId = inventory.getStore().getId();
        this.product = new ProductyDTO(inventory);
    }

    @Getter
    @Setter
    public static class ProductyDTO {
        private Long id;
        private String name;
        private String category;
        private double price;
        private String sku;
        private int quantity;

        public ProductyDTO(Inventory inventory) {
            this.id = inventory.getProduct().getId();
            this.name = inventory.getProduct().getName();
            this.category = inventory.getProduct().getCategory();
            this.price = inventory.getProduct().getPrice();
            this.sku = inventory.getProduct().getSku();
            this.quantity = inventory.getQuantity();
        }
    }
}