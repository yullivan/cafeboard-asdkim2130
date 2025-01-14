package cafeboard;

import cafeboard.Board.BoardRepository;
import cafeboard.Board.BoardRequest;
import cafeboard.Board.BoardResponse;
import cafeboard.Comment.Comment;
import cafeboard.Comment.CommentRequest;
import cafeboard.Comment.CommentResponse;
import cafeboard.Comment.UpdateCommentRequest;
import cafeboard.Post.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void 게시판생성테스트(){
        BoardResponse boardResponse = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new BoardRequest("게시판 제목"))
                .when()
                .post("boards")
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(BoardResponse.class);

        assertThat(boardResponse.boardId()).isGreaterThan(0);
    }

    @Test
    public void 게시판조회테스트(){
        //생성
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new BoardRequest("게시판 제목"))
                .when()
                .post("boards")
                .then().log().all()
                .statusCode(200);


        List<BoardResponse> boardResponse = RestAssured.given().log().all()
                .when()
                .get("boards")
                .then().log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".",  BoardResponse.class);


        assertThat(boardResponse.size()).isEqualTo(1);
    }

    @Test
    public void 게시판수정테스트() {
        //생성
        long boardId = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new BoardRequest("게시판 제목"))
                .when()
                .post("boards")
                .then().log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getLong("boardId");

        //수정
        String updatedTitle = "수정된 제목";

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("boardId", boardId)
                .body(new BoardRequest(updatedTitle))
                .when()
                .put("/boards/{boardId}")
                .then()
                .log().all()
                .statusCode(200);

        //수정 조회
        List<BoardResponse> boardResponse = RestAssured.given().log().all()
                .when()
                .get("boards")
                .then().log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", BoardResponse.class);

        assertThat(boardResponse).anyMatch(boardResponse1 -> boardResponse1.title().equals("수정된 제목"));

    }

    @Test
    public void 댓글생성테스트(){
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new CommentRequest("댓글 작성 내용"))
                .when()
                .post("comments")
                .then().log().all()
                .statusCode(200);
    }
    //Comment Class Post post;에 @JoinColumn(name = "post_id", nullable = false) 주입후에는 test 실패

    @Test
    public void 댓글수정테스트() {
        //post로 댓글생성
        Long commentId =
                RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new CommentRequest("기존 댓글"))
                .when()
                .post("/comments")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getLong("commentId");

        //put으로 수정
        String updatedContent = "수정된 댓글";
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new UpdateCommentRequest(updatedContent))
                .when()
                .put("/comments/{commentId}", commentId)
                .then()
                .log().all()
                .statusCode(200);

        //get으로 조회
        String commentContents = RestAssured.given().log().all()
                .when()
                .get("/comments/{commentId}", commentId)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("content");

        assertThat(commentContents).isEqualTo(updatedContent);
    }
    //post과정 없이 외부에서 Long commnetId와 content를 줬을 경우 put 단계 test에서 500에러 출력



    @Test
    public void 게시글생성테스트(){
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new PostRequest("게시글제목", "게시글내용"))
                .when()
                .post("posts")
                .then().log().all()
                .statusCode(200);

    }

    @Test
    public void 게시글상세조회테스트(){

        PostResponse response
                = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new PostRequest("게시글제목", "게시글내용"))
                .when()
                .post("posts")
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(PostResponse.class);

        DetailPostResponse detailPostResponse
                = RestAssured.given().log().all()
                .pathParam("postId", response.postId())
                .when()
                .get("/posts/{postId}") // 서버로 GET /products 요청
                .then().log().all()
                .statusCode(200) // 요청에 대한 서버 응답의 상태코드가 200인지 검증
                .extract()
                .as(DetailPostResponse.class);

        assertThat(detailPostResponse.postId()).isEqualTo(response.postId());
        assertThat(detailPostResponse.postTitle()).isEqualTo("게시글제목");
        assertThat(detailPostResponse.content()).isEqualTo("게시글내용");
    }




}
