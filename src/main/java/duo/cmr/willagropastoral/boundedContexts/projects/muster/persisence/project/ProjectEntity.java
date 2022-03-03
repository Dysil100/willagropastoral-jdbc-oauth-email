package duo.cmr.willagropastoral.boundedContexts.projects.muster.persisence.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("project")
@Getter
@Setter
@NoArgsConstructor
public class ProjectEntity {

    @Id
    private Long id;
    private String name;
    private String startDate;
    private String endDate;

    public ProjectEntity(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
