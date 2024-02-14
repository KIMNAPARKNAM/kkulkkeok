package io.kimnaparknam.kkulkkeok.post;

import io.kimnaparknam.kkulkkeok.category.Category;
import io.kimnaparknam.kkulkkeok.category.CategoryRepository;
import io.kimnaparknam.kkulkkeok.category.CategoryService;
import io.kimnaparknam.kkulkkeok.security.UserDetailsImpl;
import io.kimnaparknam.kkulkkeok.user.User;
import io.kimnaparknam.kkulkkeok.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    //게시글 리스트 조회
    public List<PostResponseDto> getPostList() {
        List<Post> postList = postRepository.findAll();
        return postList.stream().map(post -> new PostResponseDto(post)).toList();
    }

    //게시글 작성
    public void createPost(CreatePostRequestDto createPostRequestDto, User user) {
        Optional<Category> categorycheck = categoryRepository.findByCategoryName(createPostRequestDto.getCategoryName());
        Category category;
        if (categorycheck.isEmpty()) {
            category = categoryService.createCategory(createPostRequestDto.getCategoryName());
        } else {
            category = categorycheck.get();
        }
        Post post = new Post(createPostRequestDto, category, user);
        postRepository.save(post);
    }

    //게시글 수정
    @Transactional
    public void modifyPost(Long postId, ModifyPostRequestDto modifyPostRequestDto, UserDetailsImpl userDetails) {
        String title = modifyPostRequestDto.getTitle();
        String contents = modifyPostRequestDto.getContents();
        Category category;
        Optional<Category> categorycheck = categoryRepository.findByCategoryName(modifyPostRequestDto.getCategoryName());
        if (categorycheck.isEmpty()) {
            category = categoryService.createCategory(modifyPostRequestDto.getCategoryName());
        } else {
            category = categorycheck.get();
        }
        Post post = postRepository.findById(postId).orElseThrow(() ->new IllegalArgumentException("해당 포스트는 존재하지 않습니다."));
        post.setTitle(title);
        post.setContents(contents);
        post.setCategory(category);
    }
}
