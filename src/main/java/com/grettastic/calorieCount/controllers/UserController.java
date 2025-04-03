package com.grettastic.calorieCount.controllers;

import com.grettastic.calorieCount.requests.MealRequest;
import com.grettastic.calorieCount.requests.UserRequest;
import com.grettastic.calorieCount.responses.MealResponse;
import com.grettastic.calorieCount.responses.UserResponse;
import com.grettastic.calorieCount.services.MealService;
import com.grettastic.calorieCount.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    private final MealService mealService;

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody @Valid UserRequest user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping("/{userId}/meals")
    public ResponseEntity<MealResponse> addMeal(@PathVariable Long userId, @RequestBody @Valid MealRequest meal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mealService.addMeal(userId, meal));
    }

    @GetMapping("{userId}/meals")
    public ResponseEntity<List<MealResponse>> getUserMeals(@PathVariable Long userId) {
        return ResponseEntity.ok(mealService.getUserMeals(userId));
    }

    @GetMapping("{userId}/reports/daily/{date}")
    public ResponseEntity<Double> getDailyCalories(@PathVariable Long userId,
                                                   @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(mealService.getDailyCalories(userId, date));
    }

    @GetMapping("{userId}/reports/check/{date}")
    public ResponseEntity<Boolean> isDailyGoalMet(@PathVariable Long userId,
                                            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {
        return ResponseEntity.ok(mealService.isDailyGoalMet(userId, date));
    }

    @GetMapping("{userId}/history")
    public ResponseEntity<Map<LocalDate, Double>> getHistory(@PathVariable Long userId,
                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(mealService.getHistory(userId, startDate, endDate));
    }

}
