package com.demo.shop.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="category_name", length = 50, nullable = false)
    private String category_name;

    @Column(name = "category_description", length = 50, nullable = false)
    private String category_description;

    @OneToMany(mappedBy = "category")
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();



    public Category(String categoryId, String s) {
        super();
    }
}
