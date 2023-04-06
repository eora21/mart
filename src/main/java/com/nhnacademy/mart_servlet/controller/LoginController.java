package com.nhnacademy.mart_servlet.controller;

import com.nhnacademy.mart_servlet.annotation.RequestMapping;
import com.nhnacademy.mart_servlet.servlet.FrontServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RequestMapping(url = "/login.do", method = RequestMapping.Method.POST)
public class LoginController implements Command {
    private static final String INIT_PARAM_ID = "admin";
    private static final String INIT_PARAM_PWD = "1234";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        log.info("id: {}, pw: {}", id, pwd);

        if (INIT_PARAM_ID.equals(id) && INIT_PARAM_PWD.equals(pwd)) {
            //session 있으면 가져오고 없으면 생성
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            session.setAttribute("money", 20000);
            return FrontServlet.REDIRECT + "/login.do";
        }

        log.error("아이디/패스워드가 일치하지 않습니다.");
        return FrontServlet.REDIRECT + "/loginForm.html";
    }
}
