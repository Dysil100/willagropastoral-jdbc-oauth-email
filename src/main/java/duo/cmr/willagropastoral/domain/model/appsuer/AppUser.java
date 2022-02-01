package duo.cmr.willagropastoral.domain.model.appsuer;

import duo.cmr.willagropastoral.persistence.appuser.AppUserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AppUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AppUserRole role;
    private Boolean locked = false;
    private Boolean enabled = false;
    public AppUser(String firstName, String lastName, String email, String password, AppUserRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public AppUserEntity toEntity() {
        return new AppUserEntity(firstName, lastName, email, password, role);
    }
}
