package cafeboard.Post;

import cafeboard.Board.Board;
import cafeboard.Comment.Comment;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String postTitle;
    private String content;

    public Post() {
    }

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;


    @OneToMany(mappedBy = "post")
    private List<Comment> comment;

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

    public List<Comment> getComment() {
        return comment;
    }
}
