package duo.cmr.willagropastoral.web.services.interfaces.repositories;

import duo.cmr.willagropastoral.persistence.database.registration.token.ConfirmationTokenEntity;

import java.util.Optional;

public interface ConfirmationTokenRepository {
    void save(ConfirmationTokenEntity token);

    Optional<ConfirmationTokenEntity> findByToken(String token);

    void updateConfirmedAt(String now, String token);

    Optional<ConfirmationTokenEntity> findByUsername(String username);

    void deleteByUsername(String email);
}
