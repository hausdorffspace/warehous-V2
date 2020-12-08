package com.warehouse.demo.model;

import javax.persistence.*;


@Entity
public class Piano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Enum<?> model;

    private Dimension dimensionOfPiano;
}
