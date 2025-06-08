package com.trainibit.labs.sistema_gestion.service;


import com.trainibit.labs.sistema_gestion.model.Store;

import java.util.List;


public interface StoreService {
    Store saveStore(Store store);
    List<Store> getStores();
    void deleteStore(Long id);

    Store getStoreById(Long id);
}
