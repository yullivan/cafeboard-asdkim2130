package cafeboard.Post;

import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public PostResponse createPost(@RequestBody PostRequest request){
        Post post = postService.create(request);

        return new PostResponse(post.getPostId(),
                post.getPostTitle());
    }

    //게시글 상세조회
    @GetMapping("/posts/{postId}")
    public DetailPostResponse findDetailPost (@PathVariable Long postId){
        return postService.findDetailPost(postId);
    }

}
