package br.com.market.demands.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "demands")
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    private Product product;

    @Transient
    private Address address;
}
