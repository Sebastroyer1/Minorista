package com.trainibit.labs.sistema_gestion.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Getter
@Setter
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "storeName")
    private String storeName;

    @Column(name = "storeAddress1")
    private String storeAddress1;
    @Column(name = "storeAddress2")
    private String storeAddress2;
    @Column(name = "storeAddress3")
    private String storeAddress3;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true) // Una tienda puede tener varios productos
    private List<Product> products = new ArrayList<>();


}
