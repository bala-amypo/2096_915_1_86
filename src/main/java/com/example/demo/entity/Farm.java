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

    // Farm name
    @Column(nullable = false)
    private String name;

    // Soil pH must be between 3.0 and 10.0 (validated in service)
    @Column(nullable = false)
    private Double soilPH;

    // Water level (liters or percentage depending on your design)
    @Column(nullable = false)
    private Double waterLevel;

    // Season must be one of: Kharif, Rabi, Zaid (validated in service)
    @Column(nullable = false)
    private String season;

    // Owner relationship: each farm belongs to one user
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
