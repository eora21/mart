package com.nhnacademy.mart_servlet.init;

import com.nhnacademy.mart_servlet.annotation.RequestMapping;
import com.nhnacademy.mart_servlet.controller.Command;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ControllerFactory {
    private final Map<String, Command> controllers = new HashMap<>();

    public void init(Set<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            Annotation[] annotations = clazz.getAnnotations();

            for (Annotation annotation : annotations) {

                if (!isController(annotation)) {
                    continue;
                }

                RequestMapping controller = (RequestMapping) annotation;
                String method = controller.method().toString();
                String url = controller.url();
                String key = method + url;
                try {
                    controllers.put(key, (Command) clazz.getDeclaredConstructor().newInstance());
                    log.info("{}: {}", key, clazz);
                    break;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    private boolean isController(Annotation annotation) {
        return annotation.annotationType().equals(RequestMapping.class);
    }

    public Command getCommand(String method, String path) {
        Command command = controllers.get(method + path);
        log.info(method + path);
        log.info("command: {}", command);
        return command;
    }
}
