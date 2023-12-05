package com.demo.shop.service.impl;

import com.demo.shop.entities.Article;
import com.demo.shop.repository.ArticleRepository;
import com.demo.shop.repository.CategoryRepository;
import com.demo.shop.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;



    @Override
    public Page<Article> getAllArticles(Pageable pageable) {
        return null;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public void saveImage(Article article) {
         articleRepository.save(article);
    }


   /* @Override
    public Article createArticle(@NotNull CreateArticleForm request) {
        Article article = articleRepository.save(Article
                .builder()
                    .article_name(request.getName())
                    .article_title(request.getTitle())
                    .article_description(request.getDescription())
                    .article_image(request.getImage())
                    .article_author_name(request.getAuthor_name())
                .build());
            Category category = Category
                .builder()
                    .category_name(request.getCategory_name())
                    .category_description(request.getCategory_des())
                .build();
        categoryRepository.save(category);
        return article;

    }

    @Override
    public void updateArticle(@NotNull EditArticleForm request) {
        Article article = articleRepository.findById(request.getId()).orElseThrow(()-> new UsernameNotFoundException("Article is not exist"));
        article.getCategory().setCategory_name(request.getCategory_name());
        article.getCategory().setCategory_description(request.getCategory_des());
        article.setArticle_name(request.getName());
        article.setArticle_title(request.getTitle());
        article.setArticle_description(request.getDescription());
        article.setArticle_author_name(request.getAuthor_name());
        article.setArticle_image(request.getImage());
        articleRepository.save(article);
    }*/

    @Override
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Article not found with ID:" + id));

        articleRepository.deleteById(id);
    }

    @Override
    public Article getArticleById(Long id) {
        return null;
    }
}
