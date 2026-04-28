package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final int MAX_REQUESTS = 10; // Giới hạn 10 lần
    private final int TIME_WINDOW = 1;   // Trong 1 phút

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIp = request.getRemoteAddr();
        String key = "rate:limit:ip:" + clientIp;

        Long count = redisTemplate.opsForValue().increment(key);

        if (count != null && count == 1) {
            redisTemplate.expire(key, TIME_WINDOW, TimeUnit.MINUTES);
        }

        if (count != null && count > MAX_REQUESTS) {
            response.setStatus(429);
            response.getWriter().write("Ban da truy cap qua han muc! Vui long doi 1 phut.");
            return false; // Chặn request
        }

        return true; // Cho phép đi tiếp
    }
}