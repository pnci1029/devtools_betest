package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.ArticleEntity;
import com.example.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public ArticleEntity postArticles(ArticleDto articleDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ArticleEntity articleEntity = new ArticleEntity(articleDto, username);
        articleRepository.save(articleEntity);
        return articleEntity;
    }

    @Transactional
    public List<ArticleEntity> getArticles() {
        List<ArticleEntity> result = articleRepository.findAll();
        return result;
    }
}
