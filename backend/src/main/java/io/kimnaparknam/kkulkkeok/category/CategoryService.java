package io.kimnaparknam.kkulkkeok.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public Category createCategory(String categoryName) {
        Category category = new Category(categoryName);
        return categoryRepository.save(category);
    }
}
