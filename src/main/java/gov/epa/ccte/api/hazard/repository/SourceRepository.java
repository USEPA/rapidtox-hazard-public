package gov.epa.ccte.api.hazard.repository;

import gov.epa.ccte.api.hazard.domain.SourceDescription;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<SourceDescription, String> {

		List<SourceDescription> findByName(String name);

		List<SourceDescription> findByNameIn(List<String> names);
}
