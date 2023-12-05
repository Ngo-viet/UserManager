package com.demo.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data

@NoArgsConstructor

public class EditArticleForm {
    private Long id;
    private String name;
    private String title;
    private String description;

    private String author_name;

    private String date;

    private MultipartFile image;

    private Long category_id;

    public EditArticleForm(Long id, String name, String title, String description, String author_name, String date, MultipartFile image, Long category_id) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
        this.author_name = author_name;
        this.date = date;
        this.image = image;
        this.category_id = category_id;
    }
}
