package com.trainibit.labs.sistema_gestion.service;

import com.trainibit.labs.sistema_gestion.model.Inventory;
import com.trainibit.labs.sistema_gestion.model.Product;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    List<Inventory> getAllInventory();
    List<Inventory> getInventoryByStore(Long storeId);
    Optional<Inventory> getInventoryById(Long id);
    Inventory saveInventory(Inventory inventory);
    void deleteInventoryById(Long id);

    Inventory getInventoryByProductId(long id);
}
