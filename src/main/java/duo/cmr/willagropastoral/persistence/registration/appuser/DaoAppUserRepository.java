package duo.cmr.willagropastoral.persistence.registration.appuser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional(readOnly = true)
public interface DaoAppUserRepository extends CrudRepository<AppUserEntity, Long> {

    Optional<AppUserEntity> findByEmail(String email);

}
