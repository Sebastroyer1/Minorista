package com.trainibit.labs.sistema_gestion.Controller;

import com.trainibit.labs.sistema_gestion.model.Store;
import com.trainibit.labs.sistema_gestion.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/add")
    public Store addStore(@RequestBody Store store) {
        return storeService.saveStore(store);
    }
}
