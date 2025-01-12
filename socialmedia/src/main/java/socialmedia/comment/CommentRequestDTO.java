package socialmedia.comment;

import lombok.Data;

@Data
public class CommentRequestDTO {

    private String content;

    private Long userId;

    private Long postId;
}
