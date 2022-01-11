package com.lg.bsp.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;

/**
 * @ClassName CookieUtil
 * @Description Cookie工具类
 * @Author jincheng
 * @Date 2022/1/7 9:14
 * @Version 1.0
 **/
public class CookieUtil {
    public static Object getCookieValue(String key) {
        if (key == null || key == "") {
            return null;
        }
        Cookie[] cookies = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getCookies();
        Object value = null;
        if (null != cookies && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (key.equals(c.getName())) {
                    value = c.getValue();
                    break;
                }
            }
        }
        return null != value ? value:null;
    }

}