package cafeboard.Board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/boards")
    public List<BoardResponse> findAllBoards(){
        return boardService.findAll();
    }
}
