package duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.CustomEmailValidator;
import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.RegistrationRequest;
import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUserRole;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.database.token.ConfirmationTokenEntity;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.repositories.UserArchivRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUserRole.*;
import static duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.DateTimeHelper.stringToDate;

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
            AppUserRole role = getRole(request.getEmail());
            AppUser appUser = new AppUser(
                    request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), role);
            return appUserService.signUpUser(appUser); // is equal to:return token
        }
        //throw new IllegalStateException("email not valid");
        return "email not valid";
    }

    private AppUserRole getRole(String email) {
        if (admins.contains(email)) return ROLE_ADMIN;
        if (leaders.contains(email)) return ROLE_LEADER;
        else return ROLE_USER;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationTokenEntity confirmationTokenEntity = confirmationTokenService.getToken(token).orElseThrow(() ->
                new IllegalStateException("token not found"));

        if (confirmationTokenEntity.getConfirmedAt() != null) {
            return "email already confirmed";
            //hrow new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = stringToDate(confirmationTokenEntity.getExpiredAt());

        if (expiredAt.isBefore(LocalDateTime.now())) {
           return appUserService.signUpUser((AppUser)
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

    /*public String disableAppUser(String token) {
        Optional<ConfirmationTokenEntity> token1 = confirmationTokenService.getToken(token);
        if (token1.isPresent()) {
            String username = token1.get().getUsername();
            appUserService.disableAppUser(username);
            return username + "'s  disable with succes.";
        }
        return "User's  token not found";
    }*/

    public void updatePassword(String email, String password) {
        System.out.println("Registrationsservice update password");
        //appUserService.enableAppUser(email);
            appUserService.setPassword(email, password);
            confirmationTokenService.updateByUsername(email);
    }
}
