package com.demo.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateArticleForm {

    private String name;
    private String title;
    private String description;

    private String author_name;

    private String date;

    private MultipartFile image;

    private Long category_id;




}
