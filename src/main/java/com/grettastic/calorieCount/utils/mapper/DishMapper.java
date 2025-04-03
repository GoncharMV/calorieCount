package com.grettastic.calorieCount.utils.mapper;

import com.grettastic.calorieCount.models.Dish;
import com.grettastic.calorieCount.responses.DishResponse;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class DishMapper {

    public DishResponse toDishResponse(Dish dish) {
        return DishResponse.builder()
                .id(dish.getId())
                .name(dish.getName())
                .caloriesPerServing(dish.getCaloriesPerServing())
                .proteins(dish.getProteins())
                .fats(dish.getFats())
                .carbs(dish.getCarbs())
                .build();
    }

    public List<DishResponse> toDishResponseList(List<Dish> dishes) {
        List<DishResponse> dishResponseList = new ArrayList<>();

        for (Dish d : dishes) {
            dishResponseList.add(toDishResponse(d));
        }
        return dishResponseList;
    }

    public static List<Long> toDishesId(List<Dish> dishes) {
        List<Long> ids = new ArrayList<>();

        for (Dish d : dishes) {
            ids.add(d.getId());
        }

        return ids;
    }
}
