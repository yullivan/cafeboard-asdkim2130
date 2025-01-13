package cafeboard.Board;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    private final BoardService boardService;


    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/boards")
    public BoardResponse createBoard(@RequestBody BoardRequest request){
        return boardService.create(request);
    }
}
