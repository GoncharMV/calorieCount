package com.grettastic.calorieCount.services;

import com.grettastic.calorieCount.models.Dish;
import com.grettastic.calorieCount.repo.DishRepository;
import com.grettastic.calorieCount.requests.DishRequest;
import com.grettastic.calorieCount.responses.DishResponse;
import com.grettastic.calorieCount.utils.mapper.DishMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DishService {
    private final DishRepository dishRepo;

    public DishResponse addDish(DishRequest dish) {
        Dish savedDish = new Dish();

        savedDish.setName(dish.getName());
        savedDish.setCaloriesPerServing(dish.getCaloriesPerServing());
        savedDish.setProteins(dish.getProteins());
        savedDish.setFats(dish.getFats());
        savedDish.setCarbs(dish.getCarbs());

        return DishMapper.toDishResponse(dishRepo.save(savedDish));
    }

    public List<DishResponse> getDishes() {
        return DishMapper.toDishResponseList(dishRepo.findAll());
    }

    public Dish getDish(Long id) {
        return dishRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Dish does not exist"));
    }
}
