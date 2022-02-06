package duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.database.appuser;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table("users")
public class AppUserEntity{

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AppUserRole role;
    private Boolean locked = false;
    private Boolean enabled = false;

    public AppUserEntity(String firstName, String lastName, String email, String password, AppUserRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
