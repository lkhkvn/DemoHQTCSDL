package com.example.demo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSession implements Serializable{
    private static final long serialVersionUID = 1L;

    private String username;
    private String role;
    private String loginTime;
}
