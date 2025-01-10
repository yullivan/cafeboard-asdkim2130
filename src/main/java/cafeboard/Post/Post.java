package cafeboard.Post;

import cafeboard.Comment.Comment;
import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String postTitle;
    private String content;

    public Post() {
    }

    public Post(Long postId, String postTitle, String content) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.content = content;

    }

    public Post(String postTitle, String content) {
        this.postTitle = postTitle;
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getContent() {
        return content;
    }


}
