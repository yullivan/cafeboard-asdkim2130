package cafeboard.Post;

import cafeboard.Comment.Comment;

public record PostResponse(Long postId,
                           String postTitle,
                           String postContent) {}
