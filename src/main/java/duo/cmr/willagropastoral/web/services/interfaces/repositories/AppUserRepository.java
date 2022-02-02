package duo.cmr.willagropastoral.web.services.interfaces.repositories;

import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;

import java.util.Optional;


public interface AppUserRepository {
   Optional<AppUser> findByEmail(String email);

    void save(AppUser appUser);

    void enableAppUser(String email);

    void deleteByEmail(String username);
}
