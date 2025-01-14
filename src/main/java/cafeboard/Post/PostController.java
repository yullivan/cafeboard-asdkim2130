package cafeboard.Post;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public PostResponse createPost(@RequestBody CreatePostRequest request){
        Post post = postService.create(request);

        return new PostResponse(post.getPostId(),
                post.getPostTitle(),
                post.getContent());
    }

    //게시글 상세조회
    @GetMapping("/posts/{postId}")
    public DetailPostResponse findDetailPost (@PathVariable Long postId){
        return postService.findDetailPost(postId);
    }

    //게시글 수정
    @PutMapping("/posts/{postId}")
    public PostResponse updatePost(@PathVariable Long postId,
                                   @RequestBody PostRequest request){
        return postService.update(postId, request);
    }

    //게시글삭제
    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.delete(postId);
    }

    //게시글 목록조회
    @GetMapping("postslist")
    public List<PostListResponse>getAllPostsList(){
        return postService.findAllPosts();
    }

}
