package com.randomizer.meal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MealJsonResponse {

    @JsonProperty
    private Meal[] meals;
}
