package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.ArticleEntity;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.util.WhatTimeIsIt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private ArticleRepository articleRepository;
//  현재시간
    private WhatTimeIsIt whatTimeIsIt;

    @Autowired

    public ArticleService(ArticleRepository articleRepository, WhatTimeIsIt whatTimeIsIt) {
        this.articleRepository = articleRepository;
        this.whatTimeIsIt = whatTimeIsIt;
    }


    @Transactional
    public ArticleEntity postArticles(ArticleDto articleDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String getTime = whatTimeIsIt.getNow();
        ArticleEntity articleEntity = new ArticleEntity(articleDto, username, getTime);
        articleRepository.save(articleEntity);
        return articleEntity;
    }

    @Transactional
    public List<ArticleEntity> getArticles() {
        List<ArticleEntity> result = articleRepository.findAll();
        return result;
    }
}
