package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List; // Import thư viện List

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // --- LẤY THEO ID (Giữ lại nếu bạn vẫn muốn test lẻ) ---
    public Product getProductFromDbOnly(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "productCache", key = "#id")
    public Product getProductWithCache(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // --- CẬP NHẬT: LẤY TẤT CẢ CÁC DÒNG (FULL) ---

    // 1. Lấy toàn bộ từ MySQL (Luôn truy vấn database)
    public List<Product> getAllProductsNoCache() {
        System.out.println("Đang quét toàn bộ MySQL...");
        return productRepository.findAll();
    }

    // 2. Lấy toàn bộ và lưu vào Redis (Chỉ quét MySQL 1 lần đầu)
    @Cacheable(value = "allProductsCache")
    public List<Product> getAllProductsWithCache() {
        System.out.println("MySQL đang làm việc để nạp dữ liệu vào Redis...");
        return productRepository.findAll();
    }
}