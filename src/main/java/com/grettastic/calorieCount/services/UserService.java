package com.grettastic.calorieCount.services;

import com.grettastic.calorieCount.enums.Gender;
import com.grettastic.calorieCount.models.User;
import com.grettastic.calorieCount.repo.UserRepository;
import com.grettastic.calorieCount.requests.UserRequest;
import com.grettastic.calorieCount.responses.UserResponse;
import com.grettastic.calorieCount.utils.CalorieCalculator;
import com.grettastic.calorieCount.utils.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserService {
    private final UserRepository userRepo;

    public UserResponse addUser(UserRequest user) {
        User savedUser = new User();
        savedUser.setName(user.getName());
        savedUser.setEmail(user.getEmail());
        savedUser.setAge(user.getAge());
        savedUser.setWeight(user.getWeight());
        savedUser.setHeight(user.getHeight());
        savedUser.setGender(user.getGender());
        savedUser.setActivityLevel(user.getActivityLevel());
        savedUser.setGoal(user.getGoal());
        savedUser.setCalNorm(CalorieCalculator.calculateCalNorm(user.getHeight(), user.getWeight(),
                user.getAge(), isUserFemale(savedUser), user.getActivityLevel()));
        return UserMapper.toUserResponse(userRepo.save(savedUser));
    }

    public UserResponse getUserById(Long id) {
        return UserMapper.toUserResponse(userRepo.findById(id).orElseThrow(()
                -> new EntityNotFoundException("User not found")));
    }

    private boolean isUserFemale(User user) {
        return user.getGender().equals(Gender.FEMALE);
    }
}
