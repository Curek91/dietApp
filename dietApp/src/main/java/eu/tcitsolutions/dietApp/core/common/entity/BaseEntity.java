package eu.tcitsolutions.dietApp.core.common.entity;

import lombok.Getter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_entity_generator")
    @SequenceGenerator(name="base_entity_generator", sequenceName = "base_entity_seq", allocationSize=1)
    @NaturalId
    protected Long id;
}
