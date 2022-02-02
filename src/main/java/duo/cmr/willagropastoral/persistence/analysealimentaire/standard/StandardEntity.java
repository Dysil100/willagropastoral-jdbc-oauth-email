package duo.cmr.willagropastoral.persistence.analysealimentaire.standard;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table("standard")
public class StandardEntity {
    @Id
    private Long id;
    private String description;
    private final Double lysine;
    private final Double methyonine;
    private final Double proteineBrute;
    private final Double energieMetabolisable;

    public StandardEntity(String description, Double lysine, Double methyonine, Double proteineBrute, Double energieMetabolisable) {
        this.description = description;
        this.lysine = lysine;
        this.methyonine = methyonine;
        this.proteineBrute = proteineBrute;
        this.energieMetabolisable = energieMetabolisable;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
