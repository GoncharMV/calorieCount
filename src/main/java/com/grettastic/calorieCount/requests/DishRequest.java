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

    @NotNull
    private double proteins;

    @NotNull
    private double fats;

    @NotNull
    private double carbs;
}
