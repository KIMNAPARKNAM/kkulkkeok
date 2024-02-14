package io.kimnaparknam.kkulkkeok.post;

import lombok.Getter;

@Getter
public class CreatePostRequestDto {
    private String title;
    private String contents;
    private String categoryName;
}
