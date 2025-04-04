package com.grettastic.calorieCount.services;

import com.grettastic.calorieCount.enums.ActivityLevel;
import com.grettastic.calorieCount.enums.Gender;
import com.grettastic.calorieCount.enums.Goal;
import com.grettastic.calorieCount.models.User;
import com.grettastic.calorieCount.repo.UserRepository;
import com.grettastic.calorieCount.requests.UserRequest;
import com.grettastic.calorieCount.responses.UserResponse;
import com.grettastic.calorieCount.utils.CalorieCalculator;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    void addUserTest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Alice");
        userRequest.setEmail("alice@example.com");
        userRequest.setGender(Gender.FEMALE);
        userRequest.setAge(28);
        userRequest.setHeight(164);
        userRequest.setWeight(66);
        userRequest.setGoal(Goal.LOSS);
        userRequest.setActivityLevel(ActivityLevel.LOW);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName(userRequest.getName());
        savedUser.setEmail(userRequest.getEmail());
        savedUser.setAge(userRequest.getAge());
        savedUser.setHeight(userRequest.getHeight());
        savedUser.setWeight(userRequest.getWeight());
        savedUser.setGender(userRequest.getGender());
        savedUser.setGoal(userRequest.getGoal());
        savedUser.setActivityLevel(userRequest.getActivityLevel());
        savedUser.setCalNorm(2000.0);

        when(userRepo.save(any(User.class))).thenReturn(savedUser);

        UserResponse userResponse = userService.addUser(userRequest);

        assertEquals("Alice", userResponse.getName(), "Name does not match");
        assertEquals("alice@example.com", userResponse.getEmail(), "Email does not match");
        assertEquals(2000.0, userResponse.getCalNorm(), "Calorie norm does not match");
        verify(userRepo).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("Alice");

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        UserResponse response = userService.getUserById(1L);

        assertEquals("Alice", response.getName());
    }

    @Test
    void testGetUserThrowsException() {
        when(userRepo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getUser(99L));
    }

}
