package com.nhnacademy.mart_servlet.init;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@Slf4j
@HandlesTypes(value = {
        com.nhnacademy.mart_servlet.annotation.RequestMapping.class
})
public class MarketInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        ControllerFactory controllerFactory = new ControllerFactory();
        controllerFactory.init(set);
        servletContext.setAttribute("controllerFactory", controllerFactory);
    }
}
