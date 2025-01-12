package socialmedia.post;

import lombok.Data;

@Data
public class PostRequestDTO {

    private String title;

    private String content;

    private Status status;

    private Long userId;
}
