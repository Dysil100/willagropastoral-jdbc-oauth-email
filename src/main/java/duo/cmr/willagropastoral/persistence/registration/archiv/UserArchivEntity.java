package duo.cmr.willagropastoral.persistence.registration.archiv;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@EqualsAndHashCode
@Table("archiv")
public class UserArchivEntity {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserArchivEntity(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
