package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication
@EnableCaching
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * [BỔ SUNG QUAN TRỌNG]
     * Mặc định, Spring Session cố gắng cấu hình Redis Keyspace Notifications (lệnh CONFIG).
     * Tuy nhiên, Redis chạy trong Docker thường chặn lệnh này vì lý do bảo mật.
     * Bean này giúp Spring bỏ qua bước cấu hình tự động đó, tránh treo kết nối.
     */
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }
}