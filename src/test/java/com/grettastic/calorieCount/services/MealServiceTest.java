package com.grettastic.calorieCount.services;

import com.grettastic.calorieCount.models.Dish;
import com.grettastic.calorieCount.models.Meal;
import com.grettastic.calorieCount.models.User;
import com.grettastic.calorieCount.repo.MealRepository;
import com.grettastic.calorieCount.requests.MealRequest;
import com.grettastic.calorieCount.responses.MealResponse;
import com.grettastic.calorieCount.utils.DishUtil;
import com.grettastic.calorieCount.utils.UserUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MealServiceTest {
    @Mock
    private MealRepository mealRepo;

    @Mock
    private DishUtil dishUtil;

    @Mock
    private UserUtil userUtil;

    @InjectMocks
    private MealService mealService;

    @Test
    void addMealTest() {
        Long userId = 1L;
        MealRequest mealRequest = new MealRequest();
        mealRequest.setDishIds(Arrays.asList(1L, 2L));

        Dish dish1 = new Dish();
        dish1.setCaloriesPerServing(200);
        Dish dish2 = new Dish();
        dish2.setCaloriesPerServing(150);

        List<Dish> dishes = Arrays.asList(dish1, dish2);

        User user = new User();
        user.setId(userId);

        Meal savedMeal = new Meal();
        savedMeal.setDishes(dishes);
        savedMeal.setUser(user);

        when(dishUtil.getDishesList(anyList())).thenReturn(dishes);
        when(userUtil.getUserById(userId)).thenReturn(user);
        when(mealRepo.save(any(Meal.class))).thenReturn(savedMeal);

        MealResponse response = mealService.addMeal(userId, mealRequest);

        assertNotNull(response);
        assertEquals(2, response.getDishesId().size());
        verify(mealRepo).save(any(Meal.class));
    }

    @Test
    void testGetDailyCalories() {
        Long userId = 1L;
        LocalDate date = LocalDate.now();
        User user = new User();
        user.setId(userId);
        user.setCalNorm(2000.0);

        Dish dish1 = new Dish();
        dish1.setCaloriesPerServing(200);
        Dish dish2 = new Dish();
        dish2.setCaloriesPerServing(150);

        Meal meal1 = new Meal();
        meal1.setDishes(Arrays.asList(dish1, dish2));
        meal1.setDate(date);

        List<Meal> meals = Arrays.asList(meal1);

        when(userUtil.getUserById(userId)).thenReturn(user);
        when(mealRepo.findByUserAndDate(user, date)).thenReturn(meals);

        Double totalCalories = mealService.getDailyCalories(userId, date);

        assertEquals(350.0, totalCalories); // 200 + 150
    }

    @Test
    void testIsDailyGoalMet() {
        Long userId = 1L;
        LocalDate date = LocalDate.now();
        User user = new User();
        user.setId(userId);
        user.setCalNorm(2000.0);

        Dish dish1 = new Dish();
        dish1.setCaloriesPerServing(200);
        Dish dish2 = new Dish();
        dish2.setCaloriesPerServing(150);

        Meal meal1 = new Meal();
        meal1.setDishes(Arrays.asList(dish1, dish2));
        meal1.setDate(date);

        List<Meal> meals = Arrays.asList(meal1);

        when(userUtil.getUserById(userId)).thenReturn(user);
        when(mealRepo.findByUserAndDate(user, date)).thenReturn(meals);

        Boolean isGoalMet = mealService.isDailyGoalMet(userId, date);

        assertTrue(isGoalMet);
    }

    @Test
    void testGetHistory() {
        Long userId = 1L;
        LocalDate startDate = LocalDate.of(2025, 4, 1);
        LocalDate endDate = LocalDate.of(2025, 4, 3);
        User user = new User();
        user.setId(userId);

        Dish dish1 = new Dish();
        dish1.setCaloriesPerServing(200);
        Dish dish2 = new Dish();
        dish2.setCaloriesPerServing(150);

        Meal meal1 = new Meal();
        meal1.setDishes(Arrays.asList(dish1, dish2));
        meal1.setDate(LocalDate.of(2025, 4, 1));

        Meal meal2 = new Meal();
        meal2.setDishes(Arrays.asList(dish1));
        meal2.setDate(LocalDate.of(2025, 4, 2));

        List<Meal> meals = Arrays.asList(meal1, meal2);

        when(userUtil.getUserById(userId)).thenReturn(user);
        when(mealRepo.findByUserAndDateBetween(user, startDate, endDate)).thenReturn(meals);

        Map<LocalDate, Double> history = mealService.getHistory(userId, startDate, endDate);

        assertEquals(2, history.size()); // две даты в истории
        assertEquals(350.0, history.get(LocalDate.of(2025, 4, 1))); // 200 + 150
        assertEquals(200.0, history.get(LocalDate.of(2025, 4, 2))); // 200
    }
}
