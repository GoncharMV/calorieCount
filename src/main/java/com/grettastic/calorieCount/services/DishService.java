package com.grettastic.calorieCount.services;

import com.grettastic.calorieCount.repo.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DishService {
    private final DishRepository dishRepo;

}
