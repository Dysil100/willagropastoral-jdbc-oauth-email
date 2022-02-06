package duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.domaininterfaces;

import org.springframework.scheduling.annotation.Async;

public interface EmailSender {
    @Async
    void send(String to, String email, String subject);

    @Async
    String buildEmail(String name, String link, String subject, String bodyMsg);

    @Async
    void buildAndSend(String name, String link, String to, String subject, String bodyMsg);
}
