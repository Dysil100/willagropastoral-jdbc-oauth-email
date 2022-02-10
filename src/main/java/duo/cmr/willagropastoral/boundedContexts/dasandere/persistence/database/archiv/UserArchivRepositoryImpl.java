package duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.database.archiv;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.RegistrationRequest;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.repositories.UserArchivRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// Write only
@Repository
@AllArgsConstructor
public class UserArchivRepositoryImpl implements UserArchivRepository {
    DaoUserArchivRepository daoUserArchivRepository;

    @Override
    public void save(RegistrationRequest request) {
        Optional<UserArchivEntity> byEmail = daoUserArchivRepository.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) {
            UserArchivEntity entity = toUserArchivEntity(request);
            daoUserArchivRepository.save(entity);
        }
    }

    private UserArchivEntity toUserArchivEntity(RegistrationRequest r) {
        return new UserArchivEntity(r.getFirstName(), r.getLastName(), r.getEmail(), r.getPassword());
    }
}
