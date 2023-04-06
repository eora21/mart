package com.nhnacademy.mart_servlet.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class CookieUtils {
    private CookieUtils() {
        throw new IllegalStateException();
    }

    public static Cookie getCookie(HttpServletRequest req, String name) {
//        return Arrays.stream(req.getCookies())
//                .filter(c -> c.getName().equals(name))
//                .findAny()
//                .orElseGet(() -> new Cookie("name", "null"));
        return Optional.ofNullable(req.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(c -> c.getName().equals(name))
                        .findFirst())
                .orElse(null);
    }
}
