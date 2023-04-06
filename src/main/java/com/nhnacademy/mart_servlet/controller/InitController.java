package com.nhnacademy.mart_servlet.controller;

import com.nhnacademy.mart_servlet.annotation.RequestMapping;
import com.nhnacademy.mart_servlet.util.PrepareUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(url = "/init.do")
public class InitController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PrepareUtils.prepareMarket(req.getServletContext());
        return "/init.jsp";
    }
}
