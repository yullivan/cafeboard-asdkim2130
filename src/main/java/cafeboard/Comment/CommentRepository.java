package cafeboard.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    int countByPost_PostId(Long postId);
}
