package com.product.management.product_management.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("Pre Handle is started");
        List<Integer> data = Arrays.asList(1,4,2,553,222,34,11,213,443,543);

        data.stream()
                .filter(val -> val %2==0)
                .forEach(System.out::println);

        log.info("Pre Handle  is completed");

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

        List<Integer> data = List.of(3,1,5,2,4,4,2,324,4,2,2,6,63,2);
        log.info("Post handle is started : " );

        data.stream()
                .filter(da -> da %2 !=0)
                .forEachOrdered(System.out::println);


        log.info("Post handle is Completed : " );

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
