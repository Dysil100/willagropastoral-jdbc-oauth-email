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

import static duo.cmr.willagropastoral.web.services.DateTimeHelper.dateToString;
import static duo.cmr.willagropastoral.web.services.DateTimeHelper.stringToDate;

// TODO: 02.02.22 Implement password recuperation;
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
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {
        System.out.println("\nMethod signUp");
        String token = "";
        boolean userExists = appUserRepository.findByEmail(appUser.getUsername()).isPresent();
        System.out.println(userExists);

        if (userExists) {
            ConfirmationTokenEntity tokenEntity = confirmationTokenService.findByUsername(appUser.getUsername())
                    .orElseThrow(() -> new IllegalStateException("Token for email" + appUser.getUsername() + " does not exist"));
            System.out.println(token);
            if (!appUser.getEnabled()) {
                if (stringToDate(tokenEntity.getExpiredAt()).isBefore(LocalDateTime.now())) {
                    String newtoken = UUID.randomUUID().toString();
                    String newLink = "http://localhost:8080/registration/confirm?token=" + newtoken;
                    ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(newtoken, dateToString(LocalDateTime.now()), dateToString(LocalDateTime.now().plusMinutes(15)), appUser.getUsername());
                    confirmationTokenService.deleteByUsername(appUser.getUsername());
                    confirmationTokenService.saveConfirmationToken(confirmationTokenEntity);
                    emailSender.buildAndSend(appUser.getFirstName(), newLink, appUser.getUsername());
                } else {
                    token = tokenEntity.getToken();
                    String link = "http://localhost:8080/registration/confirm?token=" + token;
                    emailSender.buildAndSend(appUser.getFirstName(), link, appUser.getUsername());
                }
            } else {
                throw new IllegalStateException("email already taken");
            }
        } else {
            // TODO check of attributes are the same and
            String encodedPassword = bCryptPasswordEncoder
                    .encode(appUser.getPassword());

            appUser.setPassword(encodedPassword);

            appUserRepository.save(appUser);

            token = UUID.randomUUID().toString();
            String link = "http://localhost:8080/registration/confirm?token=" + token;

            ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(token, dateToString(LocalDateTime.now()), dateToString(LocalDateTime.now().plusMinutes(15)), appUser.getUsername());

            confirmationTokenService.saveConfirmationToken(confirmationTokenEntity);

            emailSender.buildAndSend(appUser.getFirstName(), link, appUser.getUsername());

            //TODO: SEND EMAIL
            // TODO if email not confirmed send confirmation email.
        }
        return token;
    }

    public void enableAppUser(String email) {
        appUserRepository.enableAppUser(email);
    }

}
