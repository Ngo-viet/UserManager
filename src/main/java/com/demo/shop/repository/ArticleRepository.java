package com.demo.shop.repository;

import com.demo.shop.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @NotNull Optional<Article> findById(@NotNull Long id);
}
