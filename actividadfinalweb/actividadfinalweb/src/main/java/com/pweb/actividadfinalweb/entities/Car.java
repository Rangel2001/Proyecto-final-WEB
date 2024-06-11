package com.pweb.actividadfinalweb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cars")
@Builder

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String model;

    @Column
    private String location;

    @Column
    private Double price;

    @OneToMany(mappedBy = "car")
    private List<Rent> rents;

}
