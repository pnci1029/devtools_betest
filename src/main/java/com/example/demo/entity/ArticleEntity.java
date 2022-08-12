package com.example.demo.entity;

import com.example.demo.dto.ArticleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userName;

//    @Column
//    private Category category;

    @Column
    private String createdTime;

    public ArticleEntity(ArticleDto articleDto,String userName, String createdTime) {
        this.title = articleDto.getTitle();
        this.content = articleDto.getContent();
        this.userName = userName;
        this.createdTime = createdTime;
    }
}
