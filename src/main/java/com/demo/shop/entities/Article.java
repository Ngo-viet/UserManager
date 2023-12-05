package com.demo.shop.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name = "tbl_article")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Article extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "article_name", length = 50, nullable = false)
    private String article_name;

    @Column(name = "article_title", length = 100, nullable = false)
    private String article_title;

    @Column(name = "article_description", length = 200, nullable = false)
    private String article_description;

    @Column(name = "article_deleted",  nullable = false)
    private int article_delete;

    @Column(name = "article_deleted_date", length = 50, nullable = false)
    private String article_deleted_date;

    @Column(name = "article_author_name", length = 50, nullable = false)
    private String article_author_name;

    @Lob
    @Column(name = "article_image")
    private String article_image;


    @Column(name = "article_date", length = 50, nullable = false)
    private String article_date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Article(long id, String article_name, String article_title, String article_description, String article_image) {
        this.id = id;
        this.article_name = article_name;
        this.article_title = article_title;
        this.article_description = article_description;
        this.article_image = article_image;

    }

    public Article(Long id, String name, String title, String description, String authorName, String image) {
        super();
    }
}
