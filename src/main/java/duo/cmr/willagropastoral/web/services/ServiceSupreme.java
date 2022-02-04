package duo.cmr.willagropastoral.web.services;

import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.web.services.subservices.AppUserService;
import duo.cmr.willagropastoral.web.services.subservices.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceSupreme {
// TODO: 03.02.22 implements all methode that the DetermineURLController class need

    private AppUserService appUserService;
    private ConfirmationTokenService confirmationTokenService;

    public AppUser getUserByEmail(String email) {
        return (AppUser) appUserService.loadUserByUsername(email);
    }

    public AppUser getUserByToken(String token) {
       return (AppUser) appUserService.loadUserByUsername(confirmationTokenService.getToken(token).get().getUsername());
    }
}
