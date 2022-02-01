package duo.cmr.willagropastoral.domain.model.token;

import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ConfirmationToken {
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
    private AppUser appUser;
}
