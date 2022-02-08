package duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.repositories;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;

import java.util.Optional;


public interface AppUserRepository {
   Optional<AppUser> findByEmail(String email);

    void save(AppUser appUser);

    void enableAppUser(String email);

    void deleteByEmail(String username);

    void disableAppUser(String email);

    void setPassword(String encode, String email);
}