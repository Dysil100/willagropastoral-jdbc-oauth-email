package duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.repositories;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.RegistrationRequest;

public interface UserArchivRepository {
    void save(RegistrationRequest request);
}
