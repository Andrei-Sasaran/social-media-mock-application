package socialmedia.user;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO maptoUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    public List<UserDTO> toUserDTO(List<User> users) {
        return users.stream()
                .map(this::maptoUserDTO)
                .collect(Collectors.toList());
    }
}
