package com.grettastic.calorieCount.utils;

import com.grettastic.calorieCount.models.Dish;
import com.grettastic.calorieCount.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
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

