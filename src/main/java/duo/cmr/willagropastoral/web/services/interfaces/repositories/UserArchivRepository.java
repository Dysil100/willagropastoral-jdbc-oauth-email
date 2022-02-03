package duo.cmr.willagropastoral.web.services.interfaces.repositories;

import duo.cmr.willagropastoral.domain.model.RegistrationRequest;

public interface UserArchivRepository {
    void save(RegistrationRequest request);
}
