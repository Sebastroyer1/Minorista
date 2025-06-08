package com.trainibit.labs.sistema_gestion.Controller;

import com.trainibit.labs.sistema_gestion.model.Store;
import com.trainibit.labs.sistema_gestion.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping()
    public List<Store> getStores() {
        return storeService.getStores();
    }

    @PostMapping("/add")
    public ResponseEntity<Store> addStore(@RequestBody Store store) {
        Store save = storeService.saveStore(store);
        return ResponseEntity.ok().body(save);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }

}
