package cafeboard.Comment;

import cafeboard.Post.Post;
import cafeboard.Post.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    //댓글생성
    public Comment create(CommentRequest request) {
        Post post = postRepository.findById(request.postId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다. 댓글을 생성할 수 없습니다.")
        );

        Comment comment = new Comment(request.content(), post);
        return commentRepository.save(comment);
    }

    public CommentResponse findByCommentId(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
                );

        return new CommentResponse(commentId,
                comment.getContent());
    }

    //댓글수정
    @Transactional
    public CommentResponse update(Long commentId, UpdateCommentRequest request){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );

        comment.setContent(request.content());

        return new CommentResponse(comment.getCommentId(),
                comment.getContent());
    }
}
