package duo.cmr.willagropastoral.persistence.registration.token;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;


@Transactional(readOnly = true)
public interface DaoConfirmationToken extends CrudRepository<ConfirmationTokenEntity, Long> {

    Optional<ConfirmationTokenEntity> findByToken(String token);

    Optional<ConfirmationTokenEntity> findByUsername(String email);

    @Transactional
    @Modifying
    @Query("UPDATE confirmation_token  SET confirmed_at = :confirmedAt WHERE token = :token")
    void updateConfirmedAt(@Param("confirmedAt") Date confirmedAt, @Param("token") String token);
}
