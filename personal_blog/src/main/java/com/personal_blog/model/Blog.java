package com.personal_blog.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category category;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modify_date")
    private LocalDateTime lastModifiedDate;

    // Function để nhận các trích đoạn
    public String getExcerpt() {
        if (content == null || content.isBlank()) {
            return "";
        }

        // Xử lý dòng trắng và xuống dòng
        String plainText = content.replaceAll("\\s+", " ").trim();
        String[] words = plainText.split(" ");

        // Nếu tổng từ <= 6 thì trả lại luôn
        if (words.length <= 6) {
            return plainText;
        }

        // Lấy 3 từ đầu và 3 từ cuối
        String firstPart = String.join(" ", Arrays.copyOfRange(words, 0, 3));
        String lastPart = String.join(" ", Arrays.copyOfRange(words, words.length - 3, words.length));

        return firstPart + " ... " + lastPart;
    }
}
