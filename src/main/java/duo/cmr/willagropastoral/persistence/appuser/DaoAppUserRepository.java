package duo.cmr.willagropastoral.persistence.appuser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional(readOnly = true)
public interface DaoAppUserRepository extends CrudRepository<AppUserEntity, Long> {

    Optional<AppUserEntity> findByEmail(String email);

}
