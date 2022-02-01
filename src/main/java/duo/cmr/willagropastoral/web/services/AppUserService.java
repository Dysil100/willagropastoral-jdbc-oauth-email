package duo.cmr.willagropastoral.web.services;

import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.persistence.registration.token.ConfirmationTokenEntity;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.AppUserRepository;
import duo.cmr.willagropastoral.web.services.interfaces.domaininterfaces.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private EmailSender emailSender;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email);
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findByEmail(appUser.getUsername()) != null;

        if (userExists) {
        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + appUser.getPassword();
            if (!appUser.getEnabled()) {
                emailSender.buildAndSend(appUser.getFirstName(), link, appUser.getUsername());
            } else {
                throw new IllegalStateException("email already taken");
            }
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + appUser.getPassword();

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), appUser);

        confirmationTokenService.saveConfirmationToken(confirmationTokenEntity);

        emailSender.buildAndSend(appUser.getFirstName(), link, appUser.getUsername());

//        TODO: SEND EMAIL

        return token;
    }

    public void enableAppUser(String email) {
        appUserRepository.enableAppUser(email);
        //return appUserRepository.findByEmail(email);
    }
}
