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

    //댓글생성(return 타입 추후 response로 수정)
    @PostMapping("/comments")
    public void createComment(@RequestBody CommentRequest request){
        commentService.create(request);
    }


}
