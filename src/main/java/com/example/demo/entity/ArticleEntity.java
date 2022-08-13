package com.example.demo.entity;

import com.example.demo.dto.ArticleDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Article")
@Data
public class ArticleEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userName;

    @Column
    private Boolean isMyArticles = true;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

//    @Column
//    private Category category;

    @Transient  // db 테이블에 안나타나게 해주는 어노테이션
    private LocalDateTime now1 = LocalDateTime.now();

    @Column(name = "CREATED_TIME", nullable = false)
    private String now = now1.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));


    public ArticleEntity(ArticleDto articleDto, String userName) {
        this.title = articleDto.getTitle();
        this.content = articleDto.getContent();
        this.category = Category.valueOf(articleDto.getCategory());
        this.userName = userName;
    }
//   수정시 게시글만 수정
    public void patch(ArticleDto dto, String time) {
        this.content = dto.getContent();
        this.now = time;
    }
}
