package com.nhnacademy.mart_servlet.controller;

import com.nhnacademy.mart_servlet.annotation.RequestMapping;
import com.nhnacademy.mart_servlet.controller.Command;
import com.nhnacademy.mart_servlet.servlet.FrontServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequestMapping(url = "/login.do")
public class LoginFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);

        if(Objects.isNull(session) || Objects.isNull(session.getAttribute("id")) ) {
            return FrontServlet.REDIRECT + "/loginForm.html";
        }

        return "/login.jsp";
    }
}
