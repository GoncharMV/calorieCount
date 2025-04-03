package com.grettastic.calorieCount.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishResponse {
    private Long id;
    private String name;
    private int caloriesPerServing;
    private double proteins;
    private double fats;
    private double carbs;
}
