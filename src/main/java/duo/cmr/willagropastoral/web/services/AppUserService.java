package duo.cmr.willagropastoral.web.services;

import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.persistence.registration.token.ConfirmationTokenEntity;
import duo.cmr.willagropastoral.web.services.interfaces.domaininterfaces.EmailSender;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
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
        String returnValue = "";
        String token = "";
        boolean userExists = appUserRepository.findByEmail(appUser.getUsername()).isPresent();
        if (userExists) {
            ConfirmationTokenEntity tokenEntity = confirmationTokenService.findByUsername(appUser.getUsername())
                    .orElseThrow(() -> new IllegalStateException("Token for email" + appUser.getUsername() + " does not exist"));
            System.out.println(token);
            if (!appUser.getEnabled()) {
                String bodyMsg = "Your actually have an account, Please click on the below link to activate your it:";
                if (stringToDate(tokenEntity.getExpiredAt()).isBefore(LocalDateTime.now())) {
                    String newtoken = UUID.randomUUID().toString();
                    String newLink = "http://localhost:8080/registration/confirm?token=" + newtoken;
                    ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(newtoken, dateToString(LocalDateTime.now()), dateToString(LocalDateTime.now().plusMinutes(15)), appUser.getUsername());
                    confirmationTokenService.deleteByUsername(appUser.getUsername());
                    confirmationTokenService.saveConfirmationToken(confirmationTokenEntity);
                    emailSender.buildAndSend(appUser.getFirstName(), newLink, appUser.getUsername(), "Confirm your Email", bodyMsg);
                    returnValue = "new token for user " + appUser.getFirstName()
                                  + " created please confirms your email to enable your account";
                } else {
                    token = tokenEntity.getToken();
                    String link = "http://localhost:8080/registration/confirm?token=" + token;
                    emailSender.buildAndSend(appUser.getFirstName(), link, appUser.getUsername(), "Confirm your Email", bodyMsg);
                    returnValue = "Please confirms your email to enable your account before the link expire";
                }
            } else {
                returnValue = "IllegalStateException: email" + appUser.getUsername() + " already taken";
                //throw new IllegalStateException("email already taken");
            }
        } else {
            // TODO check of attributes are the same and
            String encodedPassword = bCryptPasswordEncoder
                    .encode(appUser.getPassword());
            appUser.setPassword(encodedPassword);
            appUserRepository.save(appUser);
            token = UUID.randomUUID().toString();
            String link = "http://localhost:8080/registration/confirm?token=" + token;
            ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(
                    token, dateToString(LocalDateTime.now()),
                    dateToString(LocalDateTime.now().plusMinutes(15)), appUser.getUsername()
            );
            confirmationTokenService.saveConfirmationToken(confirmationTokenEntity);
            String bodyMsg = "Thank you for registering. Please click on the below link to activate your account:";
            emailSender.buildAndSend(appUser.getFirstName(), link, appUser.getUsername(), "Confirm your Email", bodyMsg);

            //TODO: SEND EMAIL
            // TODO if email not confirmed send confirmation email.
            returnValue = "Please confirms your email to enable your account";
        }
        return returnValue;
    }

    public void enableAppUser(String email) {
        appUserRepository.enableAppUser(email);
    }

    public void deleteByEmail(String username) {
        appUserRepository.deleteByEmail(username);
    }

    public String recoveryPassword(String email) {
        Optional<AppUser> byEmail = appUserRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            Optional<ConfirmationTokenEntity> byUsername = confirmationTokenService.findByUsername(email);
            String token = "";
            if (byUsername.isPresent()) token += byUsername.get().getToken();
            String link = getLinkDeleteWith(token);
            String name = byEmail.get().getFirstName();
            String bodyMsg = """
                    We don't still yet implemented an effizient way for recovering password, so that you may
                    delete your account an make another registration to marks your new password.<br> Click on the below link to register again
                    """;
            emailSender.buildAndSend(name, getLinkDeleteWith(token), email, "Password recovery", bodyMsg);
            return "ready for a deleting the account a register another";
        }
        return "Account with email " + email + " does not exist";
    }

    private String getLinkDeleteWith(String token) {
        return "http://localhost:8080/delete/confirm?token=" + token;
    }
}
