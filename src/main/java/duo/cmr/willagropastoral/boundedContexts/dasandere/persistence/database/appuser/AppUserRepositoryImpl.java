package duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.database.appuser;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class AppUserRepositoryImpl implements AppUserRepository {
    private final DaoAppUserRepository daoAppUserRepository;

    @Override
    public Optional<AppUser> findByEmail(String email) {
        return daoAppUserRepository.findByEmail(email).map(this::toAppUser);
    }

    @Override
    public void save(AppUser appUser) {
        daoAppUserRepository.save(toEntity(appUser));
    }

    @Override
    public void enableAppUser(String email) {
        daoAppUserRepository.updateEnabledUser(email);
    }

    @Override
    public void deleteByEmail(String email) {
        daoAppUserRepository.deleteByEmail(email);
    }

    @Override
    public void disableAppUser(String email) {
        daoAppUserRepository.updateDisableUser(email);
    }

    @Override
    public void updatePassword(String encode, String email) {
        daoAppUserRepository.updatePassword(encode, email);
    }

    public AppUser toAppUser(AppUserEntity entity) {
        AppUser appUser = new AppUser(entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPassword(), entity.getRole());
        appUser.setEnabled(entity.getEnabled());
        appUser.setLocked(entity.getLocked());
        return appUser;
    }

    public AppUserEntity toEntity(AppUser user) {
        return new AppUserEntity(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
