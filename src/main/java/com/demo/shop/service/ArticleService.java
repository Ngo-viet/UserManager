package com.demo.shop.service;

import com.demo.shop.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public interface ArticleService {
    Page<Article> getAllArticles(Pageable pageable);
    List<Article> getAllArticles();

     void saveImage(Article article);



    //Article createArticle(CreateArticleForm request);

    //void updateArticle(EditArticleForm request);

    void deleteArticle(Long id);

    Article getArticleById(Long id);



}
