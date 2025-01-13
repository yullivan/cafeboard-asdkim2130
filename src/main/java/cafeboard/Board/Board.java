package cafeboard.Board;

import cafeboard.Post.Post;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    public String title;


    public Board() {
    }

    public Board(String title) {
        this.title = title;
    }

    public Board(Long boardId, String title) {
        this.boardId = boardId;
        this.title = title;

    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }




}
