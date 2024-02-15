package io.kimnaparknam.kkulkkeok.post;

import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long postId;
    private String title;
    private String contents;
    private String nickname;
    private String categoryName;


    public PostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.nickname = post.getUser().getNickname();
        this.categoryName = post.getCategory().getCategoryName();
    }
}
