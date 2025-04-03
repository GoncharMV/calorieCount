package com.grettastic.calorieCount.responses;

import com.grettastic.calorieCount.enums.ActivityLevel;
import com.grettastic.calorieCount.enums.Gender;
import com.grettastic.calorieCount.enums.Goal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String name;
    private String email;
    private int age;
    private double weight;
    private double height;
    private Gender gender;
    private ActivityLevel activityLevel;
    private Goal goal;
    private double calNorm;
}
