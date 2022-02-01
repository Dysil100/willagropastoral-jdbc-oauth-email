package duo.cmr.willagropastoral.persistence.analysealimentaire.standard;
import duo.cmr.willagropastoral.domain.model.apportNutritifs.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

// TODO: 27.01.22 Fix relation standard does not exist maybe grammatikal error
@Table("standard")
public class StandardEntity {
    @Id
    private Long id;
    private String description;
    private Double lysine;
    private Double methyonine;
    private Double proteineBrute;
    private Double energieMetabolisable;

    public StandardEntity() {
        this(null, .0, .0, .0, .0);
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Standard toStandard() {
        String standard = "standard";
        return new Standard(description, new Lysine(lysine, standard), new Methyonine(methyonine, standard),
                new ProteineBrute(proteineBrute, standard), new EnergieMethabolisable(energieMetabolisable, standard));
    }
}
