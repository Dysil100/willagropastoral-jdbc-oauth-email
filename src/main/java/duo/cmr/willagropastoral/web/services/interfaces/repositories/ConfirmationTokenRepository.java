package duo.cmr.willagropastoral.web.services.interfaces.repositories;

import duo.cmr.willagropastoral.persistence.registration.token.ConfirmationTokenEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository {
    void save(ConfirmationTokenEntity token);

    Optional<ConfirmationTokenEntity> findByToken(String token);

    void updateConfirmedAt(String token, LocalDateTime now);
}
