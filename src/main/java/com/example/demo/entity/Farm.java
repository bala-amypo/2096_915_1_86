package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "farms")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double soilPH;

    private Double waterLevel;

    private String season;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
