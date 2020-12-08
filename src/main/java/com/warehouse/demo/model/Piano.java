package com.warehouse.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Piano implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StainwayModel model;

    @Enumerated(EnumType.STRING)
    private Producer producer;

    @Column(name = "is_borrowed")
    private Boolean avaliable = true;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User rentier;

}
