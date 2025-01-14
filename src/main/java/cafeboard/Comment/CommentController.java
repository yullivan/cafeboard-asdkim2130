package cafeboard.Comment;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("comments/{commentId}")
    public CommentResponse findComment(@PathVariable Long commentId){
        return commentService.findByCommentId(commentId);
    }


    @PutMapping("comments/{commentId}")
    public CommentResponse updateComment(@PathVariable Long commentId,
                                         @RequestBody UpdateCommentRequest updateRequest){

        return commentService.update(commentId, updateRequest);
    }

    @DeleteMapping("comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId){

        commentService.delete(commentId);
    }

    @GetMapping("commentslist")
    public List<CommentsListResponse> findAllCommentsList(){
        return commentService.findAll();
    }

}
