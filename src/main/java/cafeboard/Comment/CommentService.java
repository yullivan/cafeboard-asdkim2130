package cafeboard.Comment;

import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    //댓글생성
    public void create(CommentRequest request) {
        Comment comment = new Comment(request.content());
        commentRepository.save(comment);

    }
}
