package duo.cmr.willagropastoral.web.services.subservices;

import duo.cmr.willagropastoral.web.services.interfaces.repositories.ConfirmationTokenRepository;
import duo.cmr.willagropastoral.persistence.database.registration.token.ConfirmationTokenEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static duo.cmr.willagropastoral.web.services.subservices.DateTimeHelper.dateToString;


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

    public void resetTokenFor(String email) {
        deleteByUsername(email);
        saveConfirmationToken(
                new ConfirmationTokenEntity(
                        UUID.randomUUID().toString(), dateToString(LocalDateTime.now()),
                        dateToString(LocalDateTime.now().plusMinutes(15)), email
                )
        );
    }
}
