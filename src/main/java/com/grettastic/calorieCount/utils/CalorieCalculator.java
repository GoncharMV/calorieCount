package com.grettastic.calorieCount.utils;

import com.grettastic.calorieCount.enums.ActivityLevel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CalorieCalculator {
    public double calculateCalNorm(double height, double weight, int age, boolean isFemale, ActivityLevel activityLevel) {
        double bmr = calcBMR(height, weight, age, isFemale);
        return bmr * activityLevel.getValue();
    }

    private double calcBMR(double height, double weight, int age, boolean isFemale) {
        if (isFemale) {
            //calc for female
            return 447.6 + (9.2 * weight) + (3.1 * height) - (4.3 * age);
        } else {
            //calc for male
            return 88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age);
        }
    }
}
