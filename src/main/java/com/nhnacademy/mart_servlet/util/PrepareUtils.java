package com.nhnacademy.mart_servlet.util;

import com.nhnacademy.mart_servlet.food.Food;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class PrepareUtils {
    private PrepareUtils() {

    }

    public static void prepareMarket(ServletContext servletContext) {
        String foods = servletContext.getInitParameter("foods");
        Map<Food, Integer> foodStand = new HashMap<>();

        Arrays.stream(foods.split(", "))
                .forEach(foodName -> {
                    String[] priceAndAmount = servletContext.getInitParameter(foodName).split(", ");
                    int price = Integer.parseInt(priceAndAmount[0]);
                    int amount = Integer.parseInt(priceAndAmount[1]);

                    Food food = new Food(foodName, price);
                    servletContext.setAttribute(foodName, food);
                    foodStand.put(food, amount);
                });

        servletContext.setAttribute("foodStand", foodStand);

        Map<Food, Integer> fs = (Map<Food, Integer>) servletContext.getAttribute("foodStand");
        fs.forEach((k, v) -> log.info(k.getName() + v));
    }
}
