package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Endpoint lấy 1 dòng (cũ)
    @GetMapping("/no-cache/{id}")
    public Product getNoCache(@PathVariable Long id) {
        return productService.getProductFromDbOnly(id);
    }

    // Endpoint lấy 1 dòng có cache (cũ)
    @GetMapping("/with-cache/{id}")
    public Product getWithCache(@PathVariable Long id) {
        return productService.getProductWithCache(id);
    }

    // --- ENDPOINT MỚI: XUẤT HIỆN HẾT CÁC DÒNG ---

    @GetMapping("/no-cache/all")
    public List<Product> getAllNoCache() {
        return productService.getAllProductsNoCache();
    }

    @GetMapping("/with-cache/all")
    public List<Product> getAllWithCache() {
        return productService.getAllProductsWithCache();
    }
}