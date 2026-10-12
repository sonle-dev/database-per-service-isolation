package com.example.inventoryservice.controller;

import com.example.inventoryservice.entity.ProductEntity;
import com.example.inventoryservice.repository.ProductRepository;
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
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final DataSource dataSource;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    // API chứng minh thông tin kết nối DB độc lập
    @GetMapping("/db-info")
    public ResponseEntity<Map<String, Object>> getDatabaseInfo() throws SQLException {
        Map<String, Object> info = new HashMap<>();
        info.put("service", "Inventory-Service");
        info.put("connectedDatabaseUrl", dataSource.getConnection().getMetaData().getURL());
        return ResponseEntity.ok(info);
    }
}