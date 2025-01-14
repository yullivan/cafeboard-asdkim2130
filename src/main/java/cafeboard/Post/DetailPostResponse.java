package cafeboard.Post;

import java.util.List;

public record DetailPostResponse(Long postId,
                                 String postTitle,
                                 String postContent,
                                 List<Comment> commentList)
{

    public record Comment (String content){}

}
