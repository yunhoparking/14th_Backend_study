package com.example.demo.domain.post.entity;

import com.example.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tables")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    public static Post create(String title , String content, String author){
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
