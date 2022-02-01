package duo.cmr.willagropastoral.persistence.appuser;

import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.domain.service.appuser.AppUserRepository;
import duo.cmr.willagropastoral.domain.service.registration.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@AllArgsConstructor
public class AppUserRepositoryImpl implements AppUserRepository {
    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final DaoAppUserRepository daoAppUserRepository;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public Optional<AppUserEntity> findByEmail(String email) {
        return daoAppUserRepository.findByEmail(email);
    }

    @Override
    public void save(AppUser appUser) {
        daoAppUserRepository.save(appUser.toEntity());
    }

    @Override
    public void enableAppUser(String email) {
        AppUser byEmail = findByEmail(email).get().toAppUser();
        if (byEmail.getEnabled()) return;
        byEmail.setEnabled(true);
        save(byEmail);
    }
}
