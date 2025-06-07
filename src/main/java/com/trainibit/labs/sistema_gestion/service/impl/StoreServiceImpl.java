package com.trainibit.labs.sistema_gestion.service.impl;

import com.trainibit.labs.sistema_gestion.Repository.StoreRepository;
import com.trainibit.labs.sistema_gestion.model.Store;
import com.trainibit.labs.sistema_gestion.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }
}