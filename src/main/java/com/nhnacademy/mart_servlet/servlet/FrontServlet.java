package com.nhnacademy.mart_servlet.servlet;

import com.nhnacademy.mart_servlet.controller.Command;
import com.nhnacademy.mart_servlet.error.Error;
import com.nhnacademy.mart_servlet.exception.AmountException;
import com.nhnacademy.mart_servlet.exception.NoCartException;
import com.nhnacademy.mart_servlet.exception.NotEnoughMoneyException;
import com.nhnacademy.mart_servlet.init.ControllerFactory;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    public static final String REDIRECT = "redirect:";
    private static final String ERROR = "error";
    private ControllerFactory controllerFactory = new ControllerFactory();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        controllerFactory = (ControllerFactory) config.getServletContext().getAttribute("controllerFactory");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doService(req, resp);
            return;
        } catch (AmountException e) {
            req.setAttribute(ERROR, Error.REQUIRE_INIT);
        } catch (NoCartException e) {
            req.setAttribute(ERROR, Error.NO_CART);
        } catch (NotEnoughMoneyException e) {
            req.setAttribute(ERROR, Error.NOT_ENOUGH_MONEY);
        } catch (Exception e) {
            req.setAttribute(ERROR, Error.NOT_VALID);
        }
        req.getRequestDispatcher("error.do").forward(req, resp);
    }

    private void doService(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getServletPath();
        String method = req.getMethod();

        Command command = getCommand(method, url);
        String view = command.execute(req, resp);

        if (view.contains(REDIRECT)) {
            resp.sendRedirect(view.substring(REDIRECT.length()));
            return;
        }
        req.getRequestDispatcher(view).forward(req, resp);
    }

    private Command getCommand(String method, String url) {
        return controllerFactory.getCommand(method, url);
    }
}
