package cafeboard.Post;

import cafeboard.Board.Board;

public record CreatePostRequest (Long boardId,
                                 String postTitle,
                                 String postContent){}
