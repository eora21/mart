package com.nhnacademy.mart_servlet.controller;

import com.nhnacademy.mart_servlet.annotation.RequestMapping;
import com.nhnacademy.mart_servlet.exception.AmountException;
import com.nhnacademy.mart_servlet.food.Food;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping(url = "/cart.do", method = RequestMapping.Method.POST)
public class CartController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> body = new HashMap<>();
        String[] foods = req.getServletContext().getInitParameter("foods").split(", ");
        Arrays.stream(foods)
                .forEach(food -> {
                    String amount = req.getParameter(food);
                    if (amount == null) {
                        throw new IllegalArgumentException();
                    }
                    if (!"".equals(amount.trim())) {
                        body.put(food, amount);
                    }
                });

        Map<Food, Integer> cart = new HashMap<>();

        body.forEach((foodName, count) -> {
            Food food = (Food) req.getServletContext().getAttribute(foodName);
            int amount = Integer.parseInt(count);

            if (amount < 0) {
                throw new NumberFormatException();
            }

            cart.put(food, amount);
        });

        Map<Food, Integer> foodStand = (Map<Food, Integer>) req.getServletContext().getAttribute("foodStand");

        if (foodStand == null) {
            throw new AmountException();
        }

        cart.forEach((food, amount) -> {
            int nowAmount = foodStand.get(food);
            if (nowAmount < amount) {
                throw new AmountException();
            }

            foodStand.put(food, nowAmount - amount);
        });

        req.getSession(false).setAttribute("cart", cart);
        return "/cartReady.jsp";

    }
}
