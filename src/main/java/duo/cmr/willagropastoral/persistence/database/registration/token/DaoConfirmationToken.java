package duo.cmr.willagropastoral.persistence.database.registration.token;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional(readOnly = true)
public interface DaoConfirmationToken extends CrudRepository<ConfirmationTokenEntity, Long> {

    Optional<ConfirmationTokenEntity> findByToken(String token);

    Optional<ConfirmationTokenEntity> findByUsername(String email);

    @Modifying @Transactional
    @Query("DELETE FROM confirmation_token WHERE username = :mail;")
    void deleteByUsername(@Param("mail")String email);

    @Transactional @Modifying
    @Query("UPDATE confirmation_token  SET confirmed_at = :confirmedAt WHERE token = :token")
    void updateConfirmedAt(@Param("confirmedAt") String confirmedAt, @Param("token") String token);
}
