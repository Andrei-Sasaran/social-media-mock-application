package socialmedia.email;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/emails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO emailRequestDTO) {
        System.out.println("EmailRequestDTO:");
        System.out.println("Subject: " + emailRequestDTO.getSubject());
        System.out.println("Body: " + emailRequestDTO.getBody());
        System.out.println("Recipients: " + emailRequestDTO.getRecipients());

        if (emailRequestDTO.getRecipients() == null || emailRequestDTO.getRecipients().isEmpty()) {
            return ResponseEntity.badRequest().body("Recipients list cannot be null or empty");
        }

        String result = emailService.sendEmail(emailRequestDTO.getRecipients(),
                emailRequestDTO.getSubject(),emailRequestDTO.getBody());
        return ResponseEntity.ok(result);
    }
}
