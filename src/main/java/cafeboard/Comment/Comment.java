package cafeboard.Comment;

import cafeboard.Post.Post;
import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;
    private int commentCount;




    public Comment() {
    }

    public Comment(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }

    public Comment(String content) {
        this.content = content;
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }
}
