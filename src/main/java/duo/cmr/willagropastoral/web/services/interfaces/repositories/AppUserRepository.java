package duo.cmr.willagropastoral.web.services.interfaces.repositories;

import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;


public interface AppUserRepository {
   AppUser findByEmail(String email);

    void save(AppUser appUser);

    void enableAppUser(String email);
}
