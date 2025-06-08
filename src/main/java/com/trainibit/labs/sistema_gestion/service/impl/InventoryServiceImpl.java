package com.trainibit.labs.sistema_gestion.service.impl;

import com.trainibit.labs.sistema_gestion.Repository.InventoryRepository;
import com.trainibit.labs.sistema_gestion.model.Inventory;
import com.trainibit.labs.sistema_gestion.service.InventoryService;
import com.trainibit.labs.sistema_gestion.service.ProductService;
import com.trainibit.labs.sistema_gestion.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private final InventoryRepository inventoryRepository;

    public  InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }
    @Override
    public List<Inventory> getInventoryByStore(Long storeId) {
        return inventoryRepository.findByStoreId(storeId);
    }
    @Override
    public Optional<Inventory> getInventoryById(Long id){
        return inventoryRepository.findById(id);
    }
    @Override
    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
    @Override
    public void deleteInventoryById(Long id){
        inventoryRepository.deleteById(id);
    }

    @Override
    public Inventory getInventoryByProductId(long productId) {
        return inventoryRepository.findByProductId(productId).orElse(null);
    }

}
