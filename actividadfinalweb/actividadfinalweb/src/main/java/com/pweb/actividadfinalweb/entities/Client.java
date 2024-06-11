package com.pweb.actividadfinalweb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "clients")
@Builder

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private String cedula;

    @Column
    private String address;

    @Column
    private String phone;

    @OneToMany(mappedBy = "client")
    private List<Rent> rents;

}
