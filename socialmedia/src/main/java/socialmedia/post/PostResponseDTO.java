package socialmedia.post;

import lombok.Data;
import socialmedia.user.UserDTO;

import java.util.Date;

@Data
public class PostResponseDTO {

    private Long id;

    private String title;

    private String content;

    private Status status;

    private Date createdOn;

    private UserDTO user;

}
