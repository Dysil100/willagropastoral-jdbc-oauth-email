package duo.cmr.willagropastoral.domain.service.email;

import org.springframework.scheduling.annotation.Async;

public interface EmailSender {
    void send(String to, String email);

    @Async
    String buildEmail(String name, String link);

    void buildAndSend(String name, String link, String to);
}
