package duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.repositories;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.database.archiv.UserArchivEntity;

import java.util.List;

public interface UserArchivRepository {
    void save(AppUser appUser);

    List<UserArchivEntity> findAll();
}
