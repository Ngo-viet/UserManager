package com.demo.shop.controller;

import com.demo.shop.dto.CreateArticleForm;
import com.demo.shop.dto.EditArticleForm;
import com.demo.shop.entities.Article;
import com.demo.shop.entities.Category;
import com.demo.shop.entities.Product;
import com.demo.shop.repository.ArticleRepository;
import com.demo.shop.service.ArticleService;
import com.demo.shop.service.CategoryService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
@SpringBootApplication
@RequestMapping("/admin/article")
public class ArticleController {
    public static String UPLOAD_DIRECTORY = System.getProperty("article.dir") + "/uploads";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;


    @GetMapping("/all")
    public String indexArticle(Model model){
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "common/article/list_article";
    }

    @RequestMapping("/add")
    public String uploadPage(Model model) {
        CreateArticleForm articledto = new CreateArticleForm();
        model.addAttribute("articledto", articledto);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "common/article/add_article";
    }

    /*@PostMapping("/save")
    public String createArticle(
            Model model,
            @RequestParam("article_name") String name,
            @RequestParam("article_title") String title,
            @RequestParam("article_description") String description,
            @RequestParam("article_author_name") String author,
            @Valid Article article,
            final @RequestParam("article_image") MultipartFile file){

            //byte[] imageData = file.getBytes();

            article.setArticle_name(article.getArticle_name());
            article.setArticle_title(article.getArticle_title());
            article.setArticle_description(article.getArticle_description());
            article.setArticle_author_name(article.getArticle_author_name());
            //article.setArticle_image(imageData);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            model.addAttribute("msg","Uploaded image: " + fileNames.toString());
            return "redirect:/admin/article/all";
    }*/

    @PostMapping("/save")
    public String addArticle(@Valid CreateArticleForm articledto, Model model){
        model.addAttribute("articledto", articledto);
        Article article = new Article();

        article.setArticle_name(articledto.getName());
        article.setArticle_title(articledto.getTitle());
        article.setArticle_description(articledto.getDescription());
        article.setArticle_author_name(articledto.getAuthor_name());
        article.setArticle_date(articledto.getDate());
        article.setArticle_deleted_date("0");
        article.setArticle_delete(0);

        //Optional<Article> optionalArticle = articleService.getArticleById(article.getId());

        String image = "Logo.png";
        Path path = Paths.get("uploads/");
        try{
            InputStream inputStream = articledto.getImage().getInputStream();
            Files.copy(inputStream, path.resolve(articledto.getImage().getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            image = articledto.getImage().getOriginalFilename().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        article.setArticle_image(image);
        //article = new Article(articledto.getId(), articledto.getName(), articledto.getTitle(), articledto.getDescription(), articledto.getAuthor_name() ,image);
        Category category = categoryService.findById(articledto.getCategory_id());
        article.setCategory(category);
        articleRepository.save(article);
        return "redirect:/admin/article/all";
    }

    @GetMapping("/edit/{id}")
    public String editArticle(@PathVariable("id") Long id, Model model){
        //Optional<Article> opArticle = Optional.ofNullable(articleService.getArticleById(id));
        Article article = articleRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Khong hop le ID:" + id));
        EditArticleForm dto = new EditArticleForm();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        if(article!= null){

            File file = new File("uploads/" + article.getArticle_image());
            FileInputStream input;
            try{
                input = new FileInputStream(file);
                MultipartFile multipartImage = new MockMultipartFile("file", file.getName(),"text/plain",
                        IOUtils.toByteArray(input));




                //dto = new EditArticleForm(dto.getId(), dto.getName(), dto.getTitle(), dto.getDescription(), dto.getAuthor_name(), dto.getDate(), multipartImage, dto.getCategory_id());

                dto.setId(article.getId());
                dto.setName(article.getArticle_name());
                dto.setTitle(article.getArticle_title());
                dto.setDescription(article.getArticle_description());
                dto.setAuthor_name(article.getArticle_author_name());
                dto.setDate(article.getArticle_date());
                dto.setImage(multipartImage);
                dto.setCategory_id(article.getCategory().getId());
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }

            model.addAttribute("EditDTO", dto);
        }else{
            model.addAttribute("EditDTO", new EditArticleForm());
        }
        return "common/article/edit_article";
    }

    @PostMapping("/update/{id}")
    public String updateArticle(@Valid EditArticleForm articledto, @PathVariable("id") Long id, @ModelAttribute("article") Article article , Model model){



        article.setId(articledto.getId());
        article.setArticle_name(articledto.getName());
        article.setArticle_title(articledto.getTitle());
        article.setArticle_description(articledto.getDescription());
        article.setArticle_author_name(articledto.getAuthor_name());
        article.setArticle_date(articledto.getDate());
        article.setArticle_deleted_date("0");
        article.setArticle_delete(0);

        //Optional<Article> optionalArticle = articleService.getArticleById(article.getId());

        String image = "Logo.png";
        Path path = Paths.get("uploads/");
        try{
            InputStream inputStream = articledto.getImage().getInputStream();
            Files.copy(inputStream, path.resolve(articledto.getImage().getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            image = articledto.getImage().getOriginalFilename().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        article.setArticle_image(image);
        //article = new Article(articledto.getId(), articledto.getName(), articledto.getTitle(), articledto.getDescription(), articledto.getAuthor_name() ,image);
        Category category = categoryService.findById(articledto.getCategory_id());
        article.setCategory(category);
        articleRepository.save(article);
        return "redirect:/admin/article/all";
    }
}