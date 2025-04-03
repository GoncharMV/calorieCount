package com.grettastic.calorieCount.responses;

import com.grettastic.calorieCount.enums.ActivityLevel;
import com.grettastic.calorieCount.enums.Gender;
import com.grettastic.calorieCount.enums.Goal;
import com.grettastic.calorieCount.models.Meal;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private int age;
    private double weight;
    private double height;
    private Gender gender;
    private ActivityLevel activityLevel;
    private Goal goal;
    private double calNorm;
    private List<Meal> meals;
}
