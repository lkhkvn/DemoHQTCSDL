🚀 REDIS-FLOW: Demo 
1. TỔNG QUAN Demo
- demo 3 bài toán cơ bản databse query caching, sesion management, rate
2. Kiến trúc hệ thống
  ### 🛠 Thông số hạ tầng (Docker)

| Thành phần | Image | Port (Host:Container) | Trạng thái |
| :--- | :--- | :---: | :---: |
| **Redis** | `redis:latest` | `6380:6379` | ✅ Active |
| **MySQL** | `mysql:8.0` | `3309:3306` | ✅ Active |
| **Backend** | `Spring Boot` | `8080:8080` | 🔄 Running |
