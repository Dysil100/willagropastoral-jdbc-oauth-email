package duo.cmr.willagropastoral.persistence.registration.appuser;

import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.web.services.ConfirmationTokenService;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class AppUserRepositoryImpl implements AppUserRepository {
    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final DaoAppUserRepository daoAppUserRepository;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public AppUser findByEmail(String email) {
        Optional<AppUserEntity> byEmail = daoAppUserRepository.findByEmail(email);
        if (byEmail.isPresent()) return toAppUser(byEmail.get());
        else throw new UsernameNotFoundException (String.format(USER_NOT_FOUND_MSG, email));
    }

    @Override
    public void save(AppUser appUser) {
        daoAppUserRepository.save(toEntity(appUser));

    }

    @Override
    public void enableAppUser(String email) {
        AppUser byEmail = findByEmail(email);
        if (byEmail.getEnabled()) return;
        byEmail.setEnabled(true);
        save(byEmail);
    }

    public AppUser toAppUser(AppUserEntity entity) {
        AppUser appUser = new AppUser(entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPassword(), entity.getRole());
        appUser.setEnabled(entity.getEnabled());
        appUser.setLocked(entity.getLocked());
        return appUser;
    }

    public AppUserEntity toEntity(AppUser user){
        return new AppUserEntity(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
