package com.trainibit.labs.sistema_gestion.Controller;

import com.trainibit.labs.sistema_gestion.dto.InventoryDTO;
import com.trainibit.labs.sistema_gestion.model.Inventory;
import com.trainibit.labs.sistema_gestion.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventory")
public class InventoryController {

    public InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Inventory> inventories = inventoryService.getAllInventory();
        if (inventories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<InventoryDTO> response = inventories.stream()
                .map(InventoryDTO::new)
                .toList();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<?> getByStore(@PathVariable Long storeId){
        List<Inventory> inventories = inventoryService.getInventoryByStore(storeId);
        if(inventories.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<InventoryDTO> response = inventories.stream()
                .map(InventoryDTO::new)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInventoryById(@RequestParam Long id){
        return inventoryService.getInventoryById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveInventory(@RequestBody Inventory inventory){
        Inventory saved = inventoryService.saveInventory(inventory);
        return ResponseEntity.ok().body(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInventoryById(@PathVariable Long id){
        inventoryService.deleteInventoryById(id);
        return ResponseEntity.ok().build();
    }
}
