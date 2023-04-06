package com.nhnacademy.mart_servlet.controller;

import com.nhnacademy.mart_servlet.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(url = "/foods.do")
public class FoodsController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String[] foods = req.getServletContext().getInitParameter("foods").split(", ");
        req.setAttribute("foods", foods);
        return "/foods.jsp";
    }
}
