package com.grettastic.calorieCount.responses;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealResponse {
    private Long id;
    private LocalDate date;
    private List<Long> dishesId;
    private Long userId;

}
