package cafeboard.Comment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/comments")
    public CommentResponse createComment(@RequestBody CommentRequest request){
        Comment comment = commentService.create(request);

        return new CommentResponse(comment.getCommentId(),
                comment.getContent());
    }


}
