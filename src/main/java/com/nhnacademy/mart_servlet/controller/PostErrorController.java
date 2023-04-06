package com.nhnacademy.mart_servlet.controller;

import com.nhnacademy.mart_servlet.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(url = "/error.do", method = RequestMapping.Method.POST)
public class PostErrorController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/error/error.jsp";
    }
}
