package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.ArticleResponseDto;
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

    //    페이지네이션 완료
//    @GetMapping("/api/articles")
//    public List<ArticleResponseDto> getArtciels(
//            @RequestParam("size") int size,
//            @RequestParam("page") int page
//    ) {
////        4. 클라이언트 입장에서는 페이지가 1페이지부터 시작해서 1빼줌
//        page = page - 1;
//        return articleService.getArticles(size, page);
//    }

    @GetMapping("/api/articles")
    public List<ArticleResponseDto> getArtciels() {
//        4. 클라이언트 입장에서는 페이지가 1페이지부터 시작해서 1빼줌
        return articleService.getArticles();
    }

    @PostMapping("/api/articles")
    public void postArticles(@RequestBody ArticleDto articleDto) {
        articleService.postArticles(articleDto);
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
