package io.kimnaparknam.kkulkkeok.post;

import io.kimnaparknam.kkulkkeok.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User targetUser);
}
