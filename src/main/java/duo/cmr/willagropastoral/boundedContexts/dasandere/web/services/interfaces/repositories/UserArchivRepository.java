package duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.repositories;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;

public interface UserArchivRepository {
    void save(AppUser appUser);
}
