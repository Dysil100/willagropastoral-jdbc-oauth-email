package duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.database.archiv;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.repositories.UserArchivRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserArchivRepositoryImpl implements UserArchivRepository {
    DaoUserArchivRepository daoUserArchivRepository;

    @Override
    public void save(AppUser appUser) {
        Optional<UserArchivEntity> byEmail = daoUserArchivRepository.findByEmail(appUser.getEmail());
        if (byEmail.isEmpty()) {
            UserArchivEntity entity = toUserArchivEntity(appUser);
            daoUserArchivRepository.save(entity);
        }
    }

    private UserArchivEntity toUserArchivEntity(AppUser user) {
        return new UserArchivEntity(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
    }
}
