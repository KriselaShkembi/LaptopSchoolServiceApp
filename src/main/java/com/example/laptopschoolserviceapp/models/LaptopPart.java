package com.example.laptopschoolserviceapp.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "laptopParts")
public class LaptopPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer stock;

    // Relationships

    @OneToMany(mappedBy = "laptopPart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets;


}
