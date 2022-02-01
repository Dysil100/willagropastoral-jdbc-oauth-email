package duo.cmr.willagropastoral.domain.service.appuser;

import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.persistence.appuser.AppUserEntity;

import java.util.Optional;

public interface AppUserRepository {
    Optional<AppUserEntity> findByEmail(String email);

    void save(AppUser appUser);

    void enableAppUser(String email);
}
