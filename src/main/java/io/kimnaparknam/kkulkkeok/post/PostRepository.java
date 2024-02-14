package io.kimnaparknam.kkulkkeok.post;

import io.kimnaparknam.kkulkkeok.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
