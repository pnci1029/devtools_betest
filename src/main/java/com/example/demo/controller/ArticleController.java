package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.ArticleEntity;
import com.example.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
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
    public List<ArticleEntity> getArticles(@ModelAttribute Model model) {
        String LoginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", LoginUsername);
        List<ArticleEntity> articleEntity = articleService.getArticles();
        return articleEntity;
    }


}
