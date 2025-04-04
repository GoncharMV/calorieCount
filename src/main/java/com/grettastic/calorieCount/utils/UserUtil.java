package com.grettastic.calorieCount.utils;

import com.grettastic.calorieCount.models.User;
import com.grettastic.calorieCount.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserUtil {
    private final UserService userService;

    public User getUserById(Long id) {
        return userService.getUser(id);
    }
}
