package io.kimnaparknam.kkulkkeok.post;

import io.kimnaparknam.kkulkkeok.category.Category;
import io.kimnaparknam.kkulkkeok.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts")
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Post (CreatePostRequestDto createPostRequestDto) {
        this.title = createPostRequestDto.getTitle();
        this.contents = createPostRequestDto.getContents();
    }

    public Post(CreatePostRequestDto createPostRequestDto, Category category, User user) {
        this.title = createPostRequestDto.getTitle();
        this.contents = createPostRequestDto.getContents();
        this.user = user;
        this.category = category;
    }

    public void update(ModifyPostRequestDto modifyPostRequestDto) {
        this.title = modifyPostRequestDto.getTitle();
        this.contents = modifyPostRequestDto.getContents();
    }
}

