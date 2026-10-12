package com.example.userservice.controller;

import com.example.userservice.entity.UserEntity;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final DataSource dataSource;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // API chứng minh thông tin kết nối DB độc lập
    @GetMapping("/db-info")
    public ResponseEntity<Map<String, Object>> getDatabaseInfo() throws SQLException {
        Map<String, Object> info = new HashMap<>();
        info.put("service", "User-Service");
        info.put("connectedDatabaseUrl", dataSource.getConnection().getMetaData().getURL());
        return ResponseEntity.ok(info);
    }
}