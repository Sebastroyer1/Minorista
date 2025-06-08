package com.trainibit.labs.sistema_gestion.Repository;

import com.trainibit.labs.sistema_gestion.model.Inventory;
import com.trainibit.labs.sistema_gestion.model.Product;
import com.trainibit.labs.sistema_gestion.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findByStoreAndProduct(Store store, Product product);
    List<Inventory> findByStoreId(Long storeId);
    Optional<Inventory> findByProductId(Long productId);

}
