package duo.cmr.willagropastoral.persistence.registration.token;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Transactional(readOnly = true)
public interface DaoConfirmationToken extends CrudRepository<ConfirmationTokenEntity, Long> {

    Optional<ConfirmationTokenEntity> findByToken(String token);

    @Transactional
    @Query("""
           UPDATE confirmation_token c
           SET c.confirmed_at = ?2
           WHERE c.token = ?1
           """)
    void updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
