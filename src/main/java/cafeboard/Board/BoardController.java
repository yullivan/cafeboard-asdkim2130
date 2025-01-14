package cafeboard.Board;

import org.springframework.web.bind.annotation.*;

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

    @PutMapping("boards/{boardId}")
    public BoardResponse updateBoard (@PathVariable Long boardId,
                                     @RequestBody BoardRequest request){

        return boardService.update(boardId, request);
    }

    @DeleteMapping("boards/{boardId}")
    public void deleteBoard(@PathVariable Long boardId){
        boardService.delete(boardId);
    }
}
