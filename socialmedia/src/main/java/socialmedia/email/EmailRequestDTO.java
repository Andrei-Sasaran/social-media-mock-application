package socialmedia.email;

import lombok.Data;

import java.util.List;

@Data
public class EmailRequestDTO {

    private List<String> recipients;

    private String subject;

    private String body;

}
