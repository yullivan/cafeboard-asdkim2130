package cafeboard.Post;

public record PostsResponse(Long postId,
                            String postTitle,
                            Comment comment) {

    public record Comment(int commentCount){}

}
