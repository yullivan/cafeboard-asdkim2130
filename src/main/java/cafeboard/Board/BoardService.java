package cafeboard.Board;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //board 생성
    public BoardResponse create (BoardRequest request){
        Board board = new Board(request.title());

                boardRepository.save(board);

        return new BoardResponse(board.getBoardId(),
                                board.getTitle());
    }

    public List<BoardResponse> findAll (){
        return boardRepository.findAll()
                .stream()
                .map(board -> new BoardResponse(
                        board.getBoardId(),
                        board.getTitle()))
                .toList();
    }
}
