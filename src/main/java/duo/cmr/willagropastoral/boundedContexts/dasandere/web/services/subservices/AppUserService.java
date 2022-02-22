package duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.database.token.ConfirmationTokenEntity;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.domaininterfaces.EmailSender;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.repositories.AppUserRepository;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.interfaces.repositories.UserArchivRepository;
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
import static duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.DateTimeHelper.stringToDate;
// TODO: 04.02.22 Whatsapp automatisieren

// TODO: 02.02.22 Implement password recuperation;
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final UserArchivRepository userArchivRepository;
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
        Optional<AppUser> byEmail = appUserRepository.findByEmail(appUser.getUsername());
        boolean userExists = byEmail.isPresent();
        if (userExists) {
            ConfirmationTokenEntity tokenEntity = confirmationTokenService.findByUsername(appUser.getUsername())
                    .orElseThrow(() -> new IllegalStateException("Token for email" + appUser.getUsername()
                                                                 + " does not exist"));
            if (!byEmail.get().getEnabled()) {
                String bodyMsg = "Your actually have an account by us, Please click on the below link to activate it:";
                if (stringToDate(tokenEntity.getExpiredAt()).isBefore(LocalDateTime.now())) {
                    String newtoken = UUID.randomUUID().toString();

                    confirmationTokenService.updateTokenFor(appUser.getUsername());
                    emailSender.buildAndSend(
                            appUser.getFirstName(), getLinkConfirmRegistration(newtoken), appUser.getUsername(), "Confirm your Email", bodyMsg
                    );
                    returnValue = "New token for user " + appUser.getFirstName()
                                  + " with email " + appUser.getEmail() + " created please confirms your email to enable your account";
                } else {
                    String token = tokenEntity.getToken();
                    emailSender.buildAndSend(appUser.getFirstName(), getLinkConfirmRegistration(token), appUser.getUsername(),
                            "Confirm your Email", bodyMsg);
                    returnValue = "Please confirms your email to enable your account before the link expire";
                }
            } else {
                returnValue = "Note: email " + appUser.getUsername() + " already have and Confirmed account by willagropastoral";
            }
        } else {
            userArchivRepository.save(appUser);
            // TODO check of attributes are the same and
            String encodedPassword = bCryptPasswordEncoder
                    .encode(appUser.getPassword());
            appUser.setPassword(encodedPassword);
            appUserRepository.save(appUser);
            String token = UUID.randomUUID().toString();
            
            confirmationTokenService.updateTokenFor(appUser.getUsername());

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
            if (byUsername.isPresent()) {
                token += byUsername.get().getToken();

                String name = byEmail.get().getFirstName();
                String bodyMsg = """
                        Are you sure you want to create a new password.<br> 
                        Click on the below link to activate the password recover.
                        """;
                emailSender.buildAndSend(name, getLinkDeleteWith(token), email, "Password recovery", bodyMsg);
                return "ready for reset the password of " + email;
            } else {
                return "No token exist for " + email;
            }
        } else {
            return "There is no account with email " + email + " registered";
        }
    }

    /**
     * @return link for building email
     * ps: change adresse for produktion
     */
    private String getLinkDeleteWith(String token) {
        return "/delete/confirm?token=" + token;
    }

    private String getLinkConfirmRegistration(String token) {
        return "/registration/confirm?token=" + token;
    }

    public void disableAppUser(String email) {
        appUserRepository.disableAppUser(email);
    }

    public void updatePassword(String password, String email) {
        appUserRepository.updatePassword(bCryptPasswordEncoder.encode(password), email);
        confirmationTokenService.updateByUsername(email);
    }

    public Optional<ConfirmationTokenEntity> getToken(String token) {
        return confirmationTokenService.getToken(token);
    }

    public void setConfirmedTokenAt(ConfirmationTokenEntity entity) {
        confirmationTokenService.setConfirmedAt(entity.getToken());
        enableAppUser(entity.getUsername());
        confirmationTokenService.updateByUsername(entity.getUsername());
    }

}
