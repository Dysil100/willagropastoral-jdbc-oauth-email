package duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.database.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table("confirmation_token")
public class ConfirmationTokenEntity {
    @Id
    private Long id;
    private String token;
    private String createdAt;
    private String expiredAt;
    private String confirmedAt;
    private String username;

    public ConfirmationTokenEntity(String token, String createdAt, String expiredAt, String username) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.username = username;
    }
}
