package duo.cmr.willagropastoral.persistence.registration.appuser;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional(readOnly = true)
public interface DaoAppUserRepository extends CrudRepository<AppUserEntity, Long> {

    Optional<AppUserEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE users  Set enabled = true  WHERE email = :mail;")
    void updateEnabledUser(@Param("mail") String email);

}
