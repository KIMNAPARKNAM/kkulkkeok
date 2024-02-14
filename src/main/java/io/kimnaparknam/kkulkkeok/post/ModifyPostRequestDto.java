package io.kimnaparknam.kkulkkeok.post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyPostRequestDto {
    private String title;
    private String contents;
    private String categoryName;
}
