package io.kimnaparknam.kkulkkeok.category;

import io.kimnaparknam.kkulkkeok.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Post> postList = new ArrayList<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
