package duo.cmr.willagropastoral.web.services;

import duo.cmr.willagropastoral.domain.EmailValidator;
import duo.cmr.willagropastoral.domain.model.RegistrationRequest;
import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.domain.model.appsuer.AppUserRole;
import duo.cmr.willagropastoral.persistence.registration.token.ConfirmationTokenEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static duo.cmr.willagropastoral.web.services.DateTimeHelper.stringToDate;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Value("${willagropastoral.admins}")
    private final List<String> admins;
    @Value("${willagropastoral.leaders}")
    private final List<String> leaders;

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {
        boolean isEmailValide = emailValidator.
                test(request.getEmail());
        if (!isEmailValide) {
            throw new IllegalStateException("email not valid");
        }
        AppUserRole role = admins.contains(request.getEmail()) ? AppUserRole.ADMIN : AppUserRole.USER;
        AppUser appUser = new AppUser(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), role);

        return appUserService.signUpUser(appUser); // is equal to:return token
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
            appUserService.signUpUser((AppUser) appUserService.loadUserByUsername(confirmationTokenEntity.getUsername()));
            //throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationTokenEntity.getUsername());
        return "confirmed";
    }
}
