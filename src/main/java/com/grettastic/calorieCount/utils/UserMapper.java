package com.grettastic.calorieCount.utils;

import com.grettastic.calorieCount.models.User;
import com.grettastic.calorieCount.responses.UserResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .weight(user.getWeight())
                .height(user.getHeight())
                .goal(user.getGoal())
                .gender(user.getGender())
                .activityLevel(user.getActivityLevel())
                .calNorm(user.getCalNorm())
                .build();
    }
}
