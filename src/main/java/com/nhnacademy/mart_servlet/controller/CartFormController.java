package com.nhnacademy.mart_servlet.controller;

import com.nhnacademy.mart_servlet.annotation.RequestMapping;
import com.nhnacademy.mart_servlet.error.Error;
import com.nhnacademy.mart_servlet.exception.NoCartException;
import com.nhnacademy.mart_servlet.food.Food;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequestMapping(url = "/cart.do")
public class CartFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Map<Food, Integer> cart = (Map<Food, Integer>) req.getSession(false).getAttribute("cart");

            int sum = cart.entrySet().stream()
                    .mapToInt(entry -> entry.getKey().getCost() * entry.getValue())
                    .reduce(Integer::sum)
                    .orElse(0);

            req.getSession().setAttribute("sum", sum);
            return "/cart.jsp";
        } catch (NullPointerException e) {
            throw new NoCartException();
        }
    }
}
