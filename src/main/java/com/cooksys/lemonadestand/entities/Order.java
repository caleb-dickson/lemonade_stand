package com.cooksys.lemonadestand.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "order_table")
@Entity
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private double total;
    @OneToMany(mappedBy = "order")
    private List<Lemonade> lemonades;
    @ManyToOne
    @JoinColumn
    private Customer customer;
    @ManyToOne
    @JoinColumn
    private LemonadeStand lemonadeStand;
}
