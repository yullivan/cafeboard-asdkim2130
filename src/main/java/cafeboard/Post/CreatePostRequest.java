package cafeboard.Post;

public record CreatePostRequest (Long boardId,
                                 String postTitle,
                                 String postContent){}
