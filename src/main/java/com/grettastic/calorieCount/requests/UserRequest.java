package com.grettastic.calorieCount.requests;

import com.grettastic.calorieCount.enums.ActivityLevel;
import com.grettastic.calorieCount.enums.Gender;
import com.grettastic.calorieCount.enums.Goal;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @Size(min = 3, max = 15, message = "Name should be no shorter then 3 and no longer then 15")
    private String name;

    @Email
    private String email;

    @Min(value = 15, message = "You should be older then 15")
    private int age;

    @Min(value = 25, message = "Invalid weight")
    @Max(value = 300, message = "Invalid weight")
    private double weight;

    @Min(value = 50, message = "Invalid height")
    @Max(value = 250, message = "Invalid height")
    private double height;

    private ActivityLevel activityLevel;

    private Gender gender;

    private Goal goal;
}
