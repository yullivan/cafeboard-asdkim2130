package cafeboard.Board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public BoardResponse update(Long boardId, BoardRequest request){
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("수정할 게시판이 존재하지 않습니다.")
        );

        board.setTitle(request.title());

        return new BoardResponse(board.getBoardId(),
                board.getTitle());
    }

    @Transactional
    public void delete(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("삭제할 게시판이 존재하지 않습니다.")
        );

        boardRepository.delete(board);

    }


}
