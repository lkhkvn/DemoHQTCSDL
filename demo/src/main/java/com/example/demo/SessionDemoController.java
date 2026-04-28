package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/auth")
public class SessionDemoController {

    private static final String SESSION_KEY = "USER_DATA";

    @GetMapping("/login")
    public String login(@RequestParam String user, HttpSession session) {
        // Giả lập lưu thông tin người dùng vào Session
        UserSession userDetails = new UserSession(
                user,
                "ADMIN",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        );

        session.setAttribute(SESSION_KEY, userDetails);
        return "Đã đăng nhập thành công cho: " + user + ". Session ID: " + session.getId();
    }

    @GetMapping("/check")
    public Object checkSession(HttpSession session) {
        UserSession userDetails = (UserSession) session.getAttribute(SESSION_KEY);

        if (userDetails == null) {
            return "Bạn chưa đăng nhập hoặc Session đã hết hạn!";
        }

        return userDetails;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Đã đăng xuất và xóa Session khỏi Redis.";
    }
}