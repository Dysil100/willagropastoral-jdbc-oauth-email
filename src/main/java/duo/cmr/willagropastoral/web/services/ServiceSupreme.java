package duo.cmr.willagropastoral.web.services;

import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.web.services.subservices.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceSupreme {
// TODO: 03.02.22 implements all methode that the DetermineURLController class need

    private AppUserService appUserService;

    public AppUser getUserByEmail(String email) {
        return (AppUser) appUserService.loadUserByUsername(email);
    }
}
