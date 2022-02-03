package duo.cmr.willagropastoral.web.services.subservices;

import duo.cmr.willagropastoral.domain.CustomEmailValidator;
import duo.cmr.willagropastoral.domain.model.RegistrationRequest;
import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.domain.model.appsuer.AppUserRole;
import duo.cmr.willagropastoral.persistence.database.registration.token.ConfirmationTokenEntity;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.UserArchivRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static duo.cmr.willagropastoral.domain.model.appsuer.AppUserRole.*;
import static duo.cmr.willagropastoral.web.services.subservices.DateTimeHelper.stringToDate;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Value("${willagropastoral.admins}")
    private final List<String> admins;
    @Value("${willagropastoral.leaders}")
    private final List<String> leaders;

    private final UserArchivRepository userArchivRepository;
    private final AppUserService appUserService;
    private final CustomEmailValidator customEmailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {
        if (customEmailValidator.test(request.getEmail())) {
            userArchivRepository.save(request);
            AppUserRole role = admins.contains(request.getEmail()) ? ADMIN :
                    ((leaders.contains(request.getEmail())) ? LEADER : USER);
            AppUser appUser = new AppUser(
                    request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), role);
            return appUserService.signUpUser(appUser); // is equal to:return token
        }
        //throw new IllegalStateException("email not valid");
        return "email not valid";
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationTokenEntity confirmationTokenEntity = confirmationTokenService.getToken(token).orElseThrow(() ->
                new IllegalStateException("token not found"));

        if (confirmationTokenEntity.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = stringToDate(confirmationTokenEntity.getExpiredAt());

        if (expiredAt.isBefore(LocalDateTime.now())) {
            appUserService.signUpUser((AppUser)
                    appUserService.loadUserByUsername(confirmationTokenEntity.getUsername()));
            //Annother signUp
            //throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationTokenEntity.getUsername());
        return "confirmed";
    }

    public String recoverPassword(String email) {
        return appUserService.recoveryPassword(email);
    }

    public String deleteTokenAndUser(String token) {
        Optional<ConfirmationTokenEntity> token1 = confirmationTokenService.getToken(token);

        if (token1.isPresent()) {
            String username = token1.get().getUsername();
            confirmationTokenService.deleteByUsername(username);
            appUserService.deleteByEmail(username);
            return username + "'s  token  deleted with succes.";
        }
        return "User's  token not found";
    }
}
