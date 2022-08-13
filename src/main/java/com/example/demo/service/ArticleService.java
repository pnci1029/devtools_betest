package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.ArticleResponseDto;
import com.example.demo.entity.ArticleEntity;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private ArticleRepository articleRepository;


    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public String rightNow() {
        LocalDateTime now1 = LocalDateTime.now();
        String time = now1.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
        return time;
    }

//    public String bringUserName() {
//        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//        User username = userRepository.findById(Long.valueOf(name)).orElseThrow(
//                () -> new NullPointerException("유저네임이 없습니다")
//        );
//        return username.getUsername();
//    }


    @Transactional
    public List<ArticleResponseDto> getArticles() {
        String LoginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ArticleEntity> articleEntity = articleRepository.findAll();
        List<ArticleResponseDto> articleResponseDtos = new ArrayList<>();
        for (ArticleEntity articles : articleEntity) {
            articleResponseDtos.add(new ArticleResponseDto(articles));
        }
        for (ArticleResponseDto datas : articleResponseDtos) {
            if (!datas.getUsername().equals(LoginUsername)) {
                datas.setIsMyArticles(Boolean.FALSE);
            }
        }
        return articleResponseDtos;
    }

    @Transactional
    public ArticleEntity postArticles(ArticleDto articleDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info(username);
        ArticleEntity articleEntity = new ArticleEntity(articleDto, username);
        articleRepository.save(articleEntity);
        return articleEntity;
    }

    @Transactional
    public ArticleEntity patchArticles(ArticleDto dto, Long id) {
//        다른 아이디 수정요청
        ArticleEntity target = articleRepository.findById(id).orElseThrow(
                () -> new IllegalIdentifierException("수정할 아이디가 없습니다.")
        );

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        다른 게시글 수정요청
        if (!target.getUserName().equals(username)) {
            throw new IllegalArgumentException("다른 사용자 게시글을 수정할 수 없습니다");
        }

//        비어있을때
        if (dto.getContent().equals("")) {
            throw new IllegalArgumentException("수정해주세요");
        }


            target.patch(dto, rightNow());
            return target;
    }

    @Transactional
    public void deleteArticles(Long id) {
        ArticleEntity target = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("삭제 할 아이디가 없습니다")
        );

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //        다른 게시글 삭제요청
        if (!target.getUserName().equals(username)) {
            throw new IllegalArgumentException("다른 사용자 게시글을 삭제할 수 없습니다");
        }
        articleRepository.delete(target);
    }
}
