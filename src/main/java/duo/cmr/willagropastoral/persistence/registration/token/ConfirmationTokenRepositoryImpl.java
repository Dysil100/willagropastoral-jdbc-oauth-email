package duo.cmr.willagropastoral.persistence.registration.token;

import duo.cmr.willagropastoral.web.services.interfaces.repositories.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ConfirmationTokenRepositoryImpl implements ConfirmationTokenRepository {
    DaoConfirmationToken daoConfirmationToken;

    @Override
    public void save(ConfirmationTokenEntity token) {
        daoConfirmationToken.save(token);
    }

    @Override
    public Optional<ConfirmationTokenEntity> findByToken(String token) {
        return daoConfirmationToken.findByToken(token);
    }

    @Override
    public void updateConfirmedAt(String token, LocalDateTime now) {
        daoConfirmationToken.updateConfirmedAt(token, now);
    }
}
