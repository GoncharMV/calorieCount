package com.grettastic.calorieCount.utils.mapper;

import com.grettastic.calorieCount.models.Meal;
import com.grettastic.calorieCount.responses.MealResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MealMapper {

    public static MealResponse toMealResponse(Meal meal) {
        return MealResponse.builder()
                .id(meal.getId())
                .date(meal.getDate())
                .userId(meal.getUser().getId())
                .dishesId(DishMapper.toDishesId(meal.getDishes()))
                .build();
    }
}
