package duo.cmr.willagropastoral.web.services.subservices;

import duo.cmr.willagropastoral.web.services.interfaces.repositories.ConfirmationTokenRepository;
import duo.cmr.willagropastoral.persistence.database.registration.token.ConfirmationTokenEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationTokenEntity token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationTokenEntity> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(Date.valueOf(LocalDate.now()), token);
    }

    public Optional<ConfirmationTokenEntity> findByUsername(String email) {
        return confirmationTokenRepository.findByUsername(email);
    }

    public void deleteByUsername(String email) {
        confirmationTokenRepository.deleteByUsername(email);
    }

}
