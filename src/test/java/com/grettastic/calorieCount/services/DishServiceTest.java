package com.grettastic.calorieCount.services;

import com.grettastic.calorieCount.models.Dish;
import com.grettastic.calorieCount.repo.DishRepository;
import com.grettastic.calorieCount.requests.DishRequest;
import com.grettastic.calorieCount.responses.DishResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DishServiceTest {
    @Mock
    private DishRepository dishRepo;

    @InjectMocks
    private DishService dishService;

    @Test
    void addDishTest() {
        DishRequest dishRequest = new DishRequest();
        dishRequest.setName("Oats");
        dishRequest.setCaloriesPerServing(150);
        dishRequest.setProteins(4.3);
        dishRequest.setFats(3.2);
        dishRequest.setCarbs(2.2);

        Dish savedDish = new Dish();
        savedDish.setId(1L);
        savedDish.setName(dishRequest.getName());
        savedDish.setCaloriesPerServing(dishRequest.getCaloriesPerServing());
        savedDish.setProteins(dishRequest.getProteins());
        savedDish.setFats(dishRequest.getFats());
        savedDish.setCarbs(dishRequest.getCarbs());

        when(dishRepo.save(any(Dish.class))).thenReturn(savedDish);

        DishResponse dishResponse = dishService.addDish(dishRequest);

        assertEquals("Oats", dishResponse.getName(), "Names does not match");
        assertEquals(150, dishResponse.getCaloriesPerServing(), "Calories does not match");
        verify(dishRepo).save(any(Dish.class));
    }

    @Test
    void getDishesTest() {
        Dish dish1 = new Dish(1L, "Rice", 150, 5, 6, 2);
        Dish dish2 = new Dish(2L, "Corn", 200, 7, 12, 3.4);

        when(dishRepo.findAll()).thenReturn(List.of(dish1, dish2));

        List<DishResponse> dishResponseList = dishService.getDishes();

        assertEquals(2, dishResponseList.size(), "List's size is lower or higher then expected");
        assertEquals("Rice", dishResponseList.get(0).getName(), "Names don't match");
        assertEquals("Corn", dishResponseList.get(1).getName(), "Names don't match");
    }
}
