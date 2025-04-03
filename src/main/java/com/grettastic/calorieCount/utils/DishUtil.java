package com.grettastic.calorieCount.utils;

import com.grettastic.calorieCount.models.Dish;
import com.grettastic.calorieCount.services.DishService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class DishUtil {

    private final DishService dishService;

    public List<Dish> getDishesList(List<Long> ids) {
        List<Dish> dishes = new ArrayList<>();

        for (Long id : ids) {
            dishes.add(dishService.getDish(id));
        }

        return dishes;
    }
}
