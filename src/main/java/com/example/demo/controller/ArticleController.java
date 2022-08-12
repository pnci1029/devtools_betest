package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.ArticleEntity;
import com.example.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/api/articles")
    public void postArticles(@RequestBody ArticleDto articleDto) {
        articleService.postArticles(articleDto);
    }

    @GetMapping("/api/articles")
    public List<ArticleEntity> getArtciels() {
        return articleService.getArticles();
    }

    @PatchMapping("/api/articles/{id}")
    public ArticleEntity patchArticles(@PathVariable Long id, @RequestBody ArticleDto dto) {
        return articleService.patchArticles(dto, id);
    }

    @DeleteMapping("/api/articles/{id}")
    public void deleteArticles(@PathVariable Long id) {
        articleService.deleteArticles(id);
    }
}
