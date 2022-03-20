package duo.cmr.willagropastoral.boundedContexts.avis.persistence;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table("avis")
public class AvisEntity {

    @Id
    private Long id;
    private String telephone;
    private String email;
    private String comment;
    private String generatedAt; // es immer ein LocaldateTime String parsen

    public AvisEntity(String telephone, String email, String comment, String generatedAt) {
        this.telephone = telephone;
        this.email = email;
        this.comment = comment;
        this.generatedAt = generatedAt;
    }



}
