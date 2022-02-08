package duo.cmr.willagropastoral.boundedContexts.dasandere.web.services;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.AppUserService;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceSupreme {
    // TODO: 05.02.22 implement all html using fragments
    private AppUserService appUserService;
    private ConfirmationTokenService confirmationTokenService;

    public AppUser getUserByEmail(String email) {
        return (AppUser) appUserService.loadUserByUsername(email);
    }

    public AppUser getUserByToken(String token) {
       return (AppUser) appUserService.loadUserByUsername(confirmationTokenService.getToken(token).get().getUsername());
    }
}