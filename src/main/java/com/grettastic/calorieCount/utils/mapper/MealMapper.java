package com.grettastic.calorieCount.utils.mapper;

import com.grettastic.calorieCount.models.Meal;
import com.grettastic.calorieCount.responses.MealResponse;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

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

    public static List<MealResponse> toMealResponseList(List<Meal> meals) {
        List<MealResponse> mealResponseList = new ArrayList<>();

        for (Meal m : meals) {
            mealResponseList.add(toMealResponse(m));
        }

        return mealResponseList;
    }
}
