package com.grettastic.calorieCount.requests;

import com.grettastic.calorieCount.enums.ActivityLevel;
import com.grettastic.calorieCount.enums.Gender;
import com.grettastic.calorieCount.enums.Goal;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @Size(min = 3, max = 15, message = "Name should be no shorter then 3 and no longer then 15")
    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @Min(value = 15, message = "You should be older then 15")
    @NotNull
    private int age;

    @Min(value = 25, message = "Invalid weight")
    @Max(value = 300, message = "Invalid weight")
    @NotNull
    private double weight;

    @Min(value = 50, message = "Invalid height")
    @Max(value = 250, message = "Invalid height")
    @NotNull
    private double height;

    @NotNull
    private ActivityLevel activityLevel;

    @NotNull
    private Gender gender;

    private Goal goal;
}
