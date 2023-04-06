package com.nhnacademy.mart_servlet.controller;

import com.nhnacademy.mart_servlet.annotation.RequestMapping;
import com.nhnacademy.mart_servlet.servlet.FrontServlet;
import com.nhnacademy.mart_servlet.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@RequestMapping(url = "/logout.do")
public class LogoutController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);

        if (Objects.nonNull(session)) {
            session.invalidate();
        }

        Cookie cookie = CookieUtils.getCookie(req, "JSESSIONID");
        if (Objects.nonNull(cookie)) {
            cookie.setValue("");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }

        return FrontServlet.REDIRECT + "/loginForm.html";
    }
}