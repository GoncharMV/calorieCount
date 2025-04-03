package com.grettastic.calorieCount.utils;

import com.grettastic.calorieCount.models.User;
import com.grettastic.calorieCount.services.UserService;
import com.grettastic.calorieCount.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUtil {
    private final UserService userService;

    public User getUserById(Long id) {
        return userService.getUser(id);
    }
}
