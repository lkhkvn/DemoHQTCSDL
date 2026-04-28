🚀 REDIS-FLOW: Demo 
1. TỔNG QUAN Demo
- demo 3 bài toán cơ bản databse query caching, sesion management, rate
2. Kiến trúc hệ thống
  ### 🛠 Thông số hạ tầng (Docker)

| Thành phần | Công nghệ | Nhiệm vụ chính | Port |
| :--- | :--- | :--- | :---: |
| **Backend Core** | Java 17, Spring Boot 3 | Xử lý Interceptor, Rate Limit Logic, REST API. | `8083` |
| **Cache Layer** | Redis 7.2 | Lưu trữ bộ đếm (Counters), Session người dùng. | `6380` |
| **Database** | MySQL 8.0 | Lưu trữ dữ liệu nghiệp vụ lâu dài. | `3309` |


▶️ Bước 1: Khởi chạy Hạ tầng (Docker)
Mở terminal tại thư mục dự án và chạy:
Bash
docker-compose up -d
Lệnh này sẽ tự động tải và chạy Redis & MySQL với cấu hình đã được tối ưu.

▶️ Bước 2: Cấu hình Backend
Mở file src/main/resources/application.properties và kiểm tra thông số:

Properties
spring.data.redis.port=6380
spring.datasource.url=jdbc:mysql://localhost:3309/demo_db
▶️ Bước 3: Chạy ứng dụng
5. HƯỚNG DẪN KIỂM THỬ (DEMO) CHI TIẾT
I. Database query caching Truy cập:
1.   truy cập vào 2 endpoint http://localhost:8081/api/no-cache/all
2. http://localhost:8081/api/with-cache/all lần 1 cache miss vì nạp dữ liệu từ MySQL vào Redis reload lại lần 2.
3. nhấn f12 cả 2 endpoit vào network để so sánh thời gian thấy dữ liệu redis truyền nhanh hơn
II. * Session Management:
Quản lý phiên người dùng tập trung, phân tán.
- Trong kiến trúc Monolithic (đơn khối) hoặc khi chỉ chạy một Server duy nhất, Session được lưu trực tiếp vào bộ nhớ RAM của chính Server đó (gọi là Sticky Session hoặc In-Memory Session) và công nghệ này được ứng dụng rộng rãi trong thực tế.
- Đăng nhập tại App 1: Truy cập http://localhost:8083/auth/login?user=NguyenVanA. Kiểm tra tại App 2: Truy cập http://localhost:8083/auth/check.
- Terminal: Gõ docker exec -it redis-container redis-cli.
Redis-cli: Gõ keys * -> Show cho mọi người xem Key đã xuất hiện.
Java: Stop Server.
Browser: Truy cập /auth/check -> Vẫn còn dữ liệu!
III.Rate Limiting: Kiểm soát tần suất truy cập API/Database.
Bước 1: Chuẩn bị môi trường quan sát: docker exec -it redis-container redis-cli monitor
Bước 2: Thực hiện kiểm thử (Execution): Mở trình duyệt và truy cập: http://localhost:8083/api/test/hello, Quan sát trình duyệt: Hiển thị dòng chữ Yeu cau thanh cong.
Bước 3: Quan sát Terminal: Xuất hiện lệnh INCR và PEXPIRE với giá trị tăng dần. Điều này chứng tỏ Redis đã ghi nhận IP của bạn.
Bước 4 Nhấn f5 qua 10 lần tronng 1 phút trình duyệt sẽ hiển thị 429 Too Many Requests: Too many requests! Vui long thu lai sau 1 phut. Hệ thống đã chặn đứng yêu cầu trước khi nó kịp chạm vào Logic xử lý nặng phía sau, giúp tiết kiệm tài nguyên Server. 
