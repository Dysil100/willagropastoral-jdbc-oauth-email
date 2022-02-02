package duo.cmr.willagropastoral.persistence.analysealimentaire.ingredient;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table("ingredient")
public class IngredientEntity {
    @Id
    Long id;
    private String name;
    private final Double lysine;
    private final Double methyonine;
    private final Double proteineBrute;
    private final Double energieMetabolisable;

    public IngredientEntity(String name, Double lysine, Double methyonine, Double proteineBrute, Double energieMetabolisable) {
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }
}
