package cafeboard.Comment;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    //댓글생성
    public Comment create(CommentRequest request) {
        Comment comment = new Comment(request.content());
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
