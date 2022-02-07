package duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.database.token.ConfirmationTokenEntity;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.domaininterfaces.EmailSender;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUserRole.ROLE_USER;
import static duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.DateTimeHelper.dateToString;
import static duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.DateTimeHelper.stringToDate;
// TODO: 04.02.22 Whatsapp automatisieren

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
        //throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        return appUserRepository.findByEmail(email).orElseGet(() -> new AppUser(
                "Not found", "Not found", "Not found", "Not found",
                ROLE_USER));
    }

    public String signUpUser(AppUser appUser) {
        String returnValue;
        boolean userExists = appUserRepository.findByEmail(appUser.getUsername()).isPresent();
        if (userExists) {
            ConfirmationTokenEntity tokenEntity = confirmationTokenService.findByUsername(appUser.getUsername())
                    .orElseThrow(() -> new IllegalStateException("Token for email" + appUser.getUsername()
                                                                 + " does not exist"));
            if (!appUser.getEnabled()) {
                String bodyMsg = "Your actually have an account, Please click on the below link to activate your it:";
                if (stringToDate(tokenEntity.getExpiredAt()).isBefore(LocalDateTime.now())) {
                    String newtoken = UUID.randomUUID().toString();
                    ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(newtoken,
                            dateToString(LocalDateTime.now()), dateToString(LocalDateTime.now().plusMinutes(15)),
                            appUser.getUsername()
                    );
                    confirmationTokenService.deleteByUsername(appUser.getUsername());
                    confirmationTokenService.saveConfirmationToken(confirmationTokenEntity);
                    emailSender.buildAndSend(
                            appUser.getFirstName(), getLinkConfirmRegistration(newtoken), appUser.getUsername(), "Confirm your Email", bodyMsg
                    );
                    returnValue = "new token for user " + appUser.getFirstName()
                                  + " created please confirms your email to enable your account";
                } else {
                    String token = tokenEntity.getToken();
                    emailSender.buildAndSend(appUser.getFirstName(), getLinkConfirmRegistration(token), appUser.getUsername(),
                            "Confirm your Email", bodyMsg);
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
            String token = UUID.randomUUID().toString();
            ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(
                    token, dateToString(LocalDateTime.now()),
                    dateToString(LocalDateTime.now().plusMinutes(15)), appUser.getUsername()
            );
            confirmationTokenService.saveConfirmationToken(confirmationTokenEntity);
            String bodyMsg = "Thank you for registering. Please click on the below link to activate your account:";
            emailSender.buildAndSend(appUser.getFirstName(), getLinkConfirmRegistration(token), appUser.getUsername(),
                    "Confirm your Email", bodyMsg);
            //TODO: SEND EMAIL
            // TODO if email not confirmed send confirmation email.
            returnValue = "Please confirms your email to enable your account";
        }
        return returnValue;
    }

    public void enableAppUser(String email) {
        System.out.println("AppUserService.enableUser( " + email + ")");
        appUserRepository.enableAppUser(email);
    }

    public void deleteByEmail(String username) {
        appUserRepository.deleteByEmail(username);
    }

    public String recoveryPassword(String email) {
        confirmationTokenService.resetTokenFor(email);
        Optional<AppUser> byEmail = appUserRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            Optional<ConfirmationTokenEntity> byUsername = confirmationTokenService.findByUsername(email);
            String token = "";
            if (byUsername.isPresent()) token += byUsername.get().getToken();
            String name = byEmail.get().getFirstName();
            String bodyMsg = """
                    Are you sure you want to create a new password.<br> 
                    Click on the below link to activate the password recover.
                    """;
            emailSender.buildAndSend(name, getLinkDeleteWith(token), email, "Password recovery", bodyMsg);
            return "ready for reset the password of " + email;
        }
        return "Account with email " + email + " does not exist";
    }

    /**
     * @return link for building email
     * ps: change adresse for produktion
     */
    private String getLinkDeleteWith(String token) {
        return "/delete/confirm?token=" + token;
        //return "http://localhost:80/delete/confirm?token=" + token;
        //return "https://willagropastoral.top/delete/confirm?token=" + token;
    }

    private String getLinkConfirmRegistration(String token) {
        return "/registration/confirm?token=" + token; //solve the problem of
        // devellopement and productions url
        //return "http://localhost:80/registration/confirm?token=" + token;
        //return "https://willagropastoral.top//registration/confirm?token=" + token;
    }

    public void disableAppUser(String email) {
        appUserRepository.disableAppUser(email);
    }

    public void setPassword(String password, String email) {
        appUserRepository.setPassword(bCryptPasswordEncoder.encode(password), email);
    }
}
