package duo.cmr.willagropastoral.persistence.registration.appuser;

import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.AppUserRepository;
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
    public void deleteByEmail(String username) {
        //Optional<AppUserEntity> byEmail = daoAppUserRepository.findByEmail(username);
        //byEmail.ifPresent(daoAppUserRepository::delete);
        daoAppUserRepository.deleteByEmail(username);

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
