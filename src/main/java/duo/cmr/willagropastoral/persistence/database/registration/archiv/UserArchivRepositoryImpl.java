package duo.cmr.willagropastoral.persistence.database.registration.archiv;

import duo.cmr.willagropastoral.domain.model.RegistrationRequest;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.UserArchivRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserArchivRepositoryImpl implements UserArchivRepository {
    DaoUserArchivRepository daoUserArchivRepository;

    @Override
    public void save(RegistrationRequest request) {
        Optional<UserArchivEntity> byEmail = daoUserArchivRepository.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) daoUserArchivRepository.save(toUserArchivEntity(request));
    }

    private UserArchivEntity toUserArchivEntity(RegistrationRequest r) {
        return new UserArchivEntity(r.getFirstName(), r.getLastName(), r.getEmail(), r.getPassword());
    }
}
