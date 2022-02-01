package duo.cmr.willagropastoral.persistence.token;

import duo.cmr.willagropastoral.domain.model.appsuer.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table("confirmation_token")
public class ConfirmationTokenEntity {
    @Id
    private Long id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    private AppUser appUser;

    public ConfirmationTokenEntity(String token, LocalDateTime createdAt, LocalDateTime expiresAt,
                                   AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}
