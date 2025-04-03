package com.grettastic.calorieCount.services;

import com.grettastic.calorieCount.models.Dish;
import com.grettastic.calorieCount.models.Meal;
import com.grettastic.calorieCount.models.User;
import com.grettastic.calorieCount.repo.MealRepository;
import com.grettastic.calorieCount.requests.MealRequest;
import com.grettastic.calorieCount.responses.MealResponse;
import com.grettastic.calorieCount.utils.DishUtil;
import com.grettastic.calorieCount.utils.UserUtil;
import com.grettastic.calorieCount.utils.mapper.MealMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MealService {
    private final MealRepository mealRepo;
    private final DishUtil dishUtil;
    private final UserUtil userUtil;

    public MealResponse addMeal(Long userId, MealRequest meal) {
        Meal savedMeal = new Meal();
        savedMeal.setDishes(dishUtil.getDishesList(meal.getDishIds()));
        savedMeal.setUser(userUtil.getUserById(userId));
        return MealMapper.toMealResponse(mealRepo.save(savedMeal));
    }

    public List<MealResponse> getUserMeals(Long userId) {
        return null;
    }

    public Double getDailyCalories(Long userId, LocalDate date) {
        User user = userUtil.getUserById(userId);

        return mealRepo.findByUserAndDate(user, date).stream()
                .flatMap(meal -> meal.getDishes().stream())
                .mapToDouble(Dish::getCaloriesPerServing)
                .sum();
    }


    public Boolean isDailyGoalMet(Long userId, LocalDate date) {
        User user = userUtil.getUserById(userId);

        double totalCal = getDailyCalories(userId, date);

        return totalCal <= user.getCalNorm();
    }

    public Map<LocalDate, Double> getHistory(Long userId, LocalDate startDate, LocalDate endDate) {
        User user = userUtil.getUserById(userId);

        return mealRepo.findByUserAndDateBetween(user, startDate, endDate).stream()
                .collect(Collectors.groupingBy(
                        Meal::getDate,
                        Collectors.summingDouble(meal -> meal.getDishes().stream()
                                .mapToDouble(Dish::getCaloriesPerServing).sum()
                        )
                ));
    }
}
