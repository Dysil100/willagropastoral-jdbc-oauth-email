package duo.cmr.willagropastoral.domain.service.analysealimentaire.persistenz.ingredient;
import duo.cmr.willagropastoral.domain.service.analysealimentaire.services.interfaces.domaininterfaces.IngredientImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("ingredient")
public class IngredientEntity {
    @Id
    Long id;
    private String name;
    private Double lysine;
    private Double methyonine;
    private Double proteineBrute;
    private Double energieMetabolisable;

    public IngredientEntity(String name, Double lysine, Double methyonine, Double proteineBrute, Double energieMetabolisable) {
        this.name = name;
        this.lysine = lysine;
        this.methyonine = methyonine;
        this.proteineBrute = proteineBrute;
        this.energieMetabolisable = energieMetabolisable;
    }

    public IngredientEntity() {
        this(null, .0, .0, .0, .0);
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredientImpl toIngredient() {
        return new IngredientImpl(name, lysine, methyonine, proteineBrute, energieMetabolisable);
    }
}
