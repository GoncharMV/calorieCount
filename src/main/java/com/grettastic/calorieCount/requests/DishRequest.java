package com.grettastic.calorieCount.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishRequest {

    @NotNull
    private String name;

    @NotNull
    private int caloriesPerServing;

    private double proteins;

    private double fats;

    private double carbs;
}
