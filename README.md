# 📝 Personal Blog Web App

Ứng dụng blog cá nhân sử dụng **Spring Boot (REST API)** và **HTML + jQuery** để thực hiện các chức năng CRUD bài viết, quản lý danh mục và phân trang.

---

## 🚀 Cách chạy nhanh

1. **Cấu hình database** trong `application.properties`:

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/personal_blog_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

2. **Chạy ứng dụng**:

   * Bằng IDE IntelliJ hoặc:

   ```bash
   ./mvnw spring-boot:run
   ```

Truy cập tại: **[http://localhost:8080](http://localhost:8080)**

---

## ✅ Tính năng chính

* CRUD bài viết (Blog)
* CRUD danh mục (Category)
* Giao diện HTML tĩnh + jQuery
* Phân trang bài viết (3 bài/trang, nút "Xem thêm")

---

## 📮 API nhanh

### 📘 Blog

| Method | Endpoint                      | Mô tả          |
| ------ | ----------------------------- | -------------- |
| GET    | `/api/v1/blogs?page=0&size=3` | Danh sách blog |
| GET    | `/api/v1/blogs/view/{id}`     | Chi tiết blog  |
| POST   | `/api/v1/blogs/create`        | Tạo mới blog   |
| PATCH  | `/api/v1/blogs/update/{id}`   | Cập nhật blog  |
| DELETE | `/api/v1/blogs/delete/{id}`   | Xóa blog       |

### 📗 Category

| Method | Endpoint                         | Mô tả              |
| ------ | -------------------------------- | ------------------ |
| GET    | `/api/v1/categories`             | Danh sách category |
| GET    | `/api/v1/categories/{id}/blogs`  | Blog theo category |
| POST   | `/api/v1/categories/create`      | Tạo mới category   |
| PATCH  | `/api/v1/categories/update/{id}` | Cập nhật category  |
| DELETE | `/api/v1/categories/delete/{id}` | Xóa category       |

---

## 🖥 Giao diện

HTML tĩnh gọi API bằng jQuery. File HTML đặt trong `src/main/resources/static/`:

| Tên file      | Mô tả                 |
| ------------- | --------------------- |
| `list.html`   | Danh sách bài viết    |
| `create.html` | Tạo bài viết mới      |
| `edit.html`   | Sửa bài viết          |
| `view.html`   | Xem chi tiết bài viết |

---

## 🧾 Mẫu JSON tạo Blog

```json
{
  "title": "Bài viết mẫu",
  "content": "Nội dung...",
  "author": "Admin",
  "categoryId": 1
}
```

---