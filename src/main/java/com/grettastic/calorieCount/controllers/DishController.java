package com.grettastic.calorieCount.controllers;

import com.grettastic.calorieCount.requests.DishRequest;
import com.grettastic.calorieCount.responses.DishResponse;
import com.grettastic.calorieCount.services.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
@Validated
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @PostMapping
    public ResponseEntity<DishResponse> addDish(@RequestBody @Valid DishRequest dish) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dishService.addDish(dish));
    }

    @GetMapping
    public ResponseEntity<List<DishResponse>> getDishes() {
        return ResponseEntity.ok(dishService.getDishes());
    }
}
