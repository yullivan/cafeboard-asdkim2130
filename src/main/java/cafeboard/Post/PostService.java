package cafeboard.Post;

import cafeboard.Comment.Comment;
import cafeboard.Comment.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
    }

    public Post create (PostRequest request){
        Post post = new Post(
                request.postTitle(),
                request.postContent());

        return postRepository.save(post);
    }


    //게시글 상세조회(댓글목록 포함)
    public DetailPostResponse findDetailPost(Long postId) {
        Post posts = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new DetailPostResponse(posts.getPostId(),
                posts.getPostTitle(),
                posts.getContent(),
                posts.getComment()
                        .stream()
                        .map(comment -> new DetailPostResponse.Comment(comment.getContent()))
                        .toList()
        );
    }

//    public List<PostsResponse> findAll(){
//        postRepository.findAll()
//                .stream()
//                .map(post -> new PostsResponse(post.getPostId(),
//                        post.getPostTitle(),
//                        post.getComment()
//                                .))



}
