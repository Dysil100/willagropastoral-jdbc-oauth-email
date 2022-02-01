package duo.cmr.willagropastoral.web.services;

import duo.cmr.willagropastoral.domain.EmailValidator;
import duo.cmr.willagropastoral.domain.model.RegistrationRequest;
import duo.cmr.willagropastoral.domain.model.appsuer.AppUserRole;
import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.persistence.registration.token.ConfirmationTokenEntity;
import duo.cmr.willagropastoral.web.services.interfaces.domaininterfaces.EmailSender;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    private final EmailSender emailSender;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        String token = appUserService.signUpUser(
                new AppUser(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(),
                        (admins.contains(request.getEmail()) ? AppUserRole.ADMIN : AppUserRole.USER)));

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        emailSender.buildAndSend(request.getFirstName(), link, request.getEmail());

        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationTokenEntity confirmationTokenEntity = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationTokenEntity.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationTokenEntity.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationTokenEntity.getAppUser().getUsername());
        return "confirmed";
    }
}
