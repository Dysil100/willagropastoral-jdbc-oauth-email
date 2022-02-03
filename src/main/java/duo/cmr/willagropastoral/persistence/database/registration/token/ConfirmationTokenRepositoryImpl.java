package duo.cmr.willagropastoral.persistence.database.registration.token;

import duo.cmr.willagropastoral.web.services.interfaces.repositories.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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
    public void updateConfirmedAt(Date now, String token) {
        daoConfirmationToken.updateConfirmedAt(now, token);
    }

    @Override
    public Optional<ConfirmationTokenEntity> findByUsername(String email) {
        return daoConfirmationToken.findByUsername(email);
    }

    @Override
    public void deleteByUsername(String email) {
        daoConfirmationToken.deleteByUsername(email);
    }
}
