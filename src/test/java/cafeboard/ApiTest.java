package cafeboard;

import cafeboard.Comment.CommentRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

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
}
