package cafeboard.Post;

public record PostListResponse(Long postId,
                               String postTitle,
                               Comment comment) {

    public record Comment(int commentCount){}

}
