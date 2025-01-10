package cafeboard;

import cafeboard.Comment.CommentRequest;
import cafeboard.Post.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
    public void 댓글생성테스트(){
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CommentRequest("댓글 작성 내용"))
                .when()
                .post("comments")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void 게시글생성테스트(){
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new PostRequest("게시글제목", "게시글내용"))
                .when()
                .post("posts")
                .then().log().all()
                .statusCode(200);

    }

    @Test
    public void 게시글상세조회테스트(){

        PostResponse response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new PostRequest("게시글제목", "게시글내용"))
                .when()
                .post("posts")
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(PostResponse.class);

        DetailPostResponse detailPostResponse = RestAssured
                .given().log().all()
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
