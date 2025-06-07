package com.trainibit.labs.sistema_gestion.Repository;

import com.trainibit.labs.sistema_gestion.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface StoreRepository extends JpaRepository<Store, Long> {

}