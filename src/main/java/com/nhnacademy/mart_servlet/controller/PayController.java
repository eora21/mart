package com.nhnacademy.mart_servlet.controller;

import com.nhnacademy.mart_servlet.annotation.RequestMapping;
import com.nhnacademy.mart_servlet.exception.NotEnoughMoneyException;
import com.nhnacademy.mart_servlet.food.Food;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@RequestMapping(url = "/pay.do", method = RequestMapping.Method.POST)
public class PayController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String foodName = req.getParameter("foodName");
        int cost = Integer.parseInt(req.getParameter("cost"));
        int money = (int) req.getSession().getAttribute("money");

        if (money < cost) {
            throw new NotEnoughMoneyException();
        }


        HttpSession session = req.getSession(false);
        session.setAttribute("money", money - cost);

        Map<Food, Integer> cart = (Map<Food, Integer>) session.getAttribute("cart");
        Food removeFood = cart.entrySet().stream()
                .map(Map.Entry::getKey)
                .filter(food -> food.getName().equals(foodName))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        cart.remove(removeFood);

        req.setAttribute("payCost", cost);
        return "/payResult.jsp";
    }
}
