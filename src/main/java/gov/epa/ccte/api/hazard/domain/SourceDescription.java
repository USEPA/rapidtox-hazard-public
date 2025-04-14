
package gov.epa.ccte.api.hazard.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "source_descriptions", schema = "rapidtox")
@Data
public class SourceDescription {
    
    @Id
    @Column(name="source_name")
    private String name;
    
    @Column(name="source_description")
    private String description;
}
