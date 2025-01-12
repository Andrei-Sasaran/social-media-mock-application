package socialmedia.post;

import org.springframework.stereotype.Component;
import socialmedia.user.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper {

    public PostResponseDTO mapToPostResponseDTO(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setStatus(post.getStatus());
        dto.setCreatedOn(post.getCreatedOn());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(post.getUser().getId());
        userDTO.setName(post.getUser().getName());
        userDTO.setEmail(post.getUser().getEmail());
        dto.setUser(userDTO);

        return dto;
    }

    public List<PostResponseDTO> toPostResponseDTO(List<Post> posts) {
        return posts.stream()
                .map(this::mapToPostResponseDTO)
                .collect(Collectors.toList());
    }

}
