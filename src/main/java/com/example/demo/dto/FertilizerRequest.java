package com.example.demo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FertilizerRequest {
    private String name;
    private String npkRatio;
    private String recommendedForCrops;
}
