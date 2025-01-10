package cafeboard.Post;

import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void create (PostRequest request){
        Post post = new Post(
                request.postTitle(),
                request.postContent());

        postRepository.save(post);
    }

}
