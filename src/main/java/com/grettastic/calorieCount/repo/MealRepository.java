package com.grettastic.calorieCount.repo;

import com.grettastic.calorieCount.models.Meal;
import com.grettastic.calorieCount.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findByUserAndDate(User user, LocalDate date);

    List<Meal> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);

    List<Meal> findAllByUser(User user);

}
