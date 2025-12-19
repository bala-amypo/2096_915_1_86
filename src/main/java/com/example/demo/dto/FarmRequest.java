
package com.example.demo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmRequest {
    private String name;
    private Double soilPH;
    private Double waterLevel;
    private String season;
}
