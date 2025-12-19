// src/main/java/com/example/demo/dto/FertilizerRequest.java
package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FertilizerRequest {
    private String name;
    private String npkRatio;
    private String recommendedForCrops;
}
