package duo.cmr.willagropastoral.domain.service.analysealimentaire.persistenz.standard;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface StandardDAO extends CrudRepository<StandardEntity, Long> {
    Optional<StandardEntity> findByDescription(String description);
}
