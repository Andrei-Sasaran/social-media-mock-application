package socialmedia.comment;

import org.springframework.stereotype.Component;
import socialmedia.post.PostDTO;
import socialmedia.user.UserDTO;

@Component
public class CommentMapper {

    public CommentResponseDTO mapToCommentResponseDTO(Comment comment) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setCreatedOn(comment.getCreatedOn());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(comment.getUser().getId());
        userDTO.setName(comment.getUser().getName());
        userDTO.setEmail(comment.getUser().getEmail());
        dto.setUser(userDTO);

        PostDTO postDTO = new PostDTO();
        postDTO.setId(comment.getPost().getId());
        postDTO.setTitle(comment.getPost().getTitle());
        postDTO.setContent(comment.getPost().getContent());
        dto.setPost(postDTO);

        return dto;
    }
}
