package socialmedia.comment;

import lombok.Data;
import socialmedia.post.PostDTO;
import socialmedia.user.UserDTO;

import java.util.Date;

@Data
public class CommentResponseDTO {

    private Long id;

    private String content;

    private UserDTO user;

    private PostDTO post;

    private Date createdOn;

}
