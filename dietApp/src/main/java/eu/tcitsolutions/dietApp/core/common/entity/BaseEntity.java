package eu.tcitsolutions.dietApp.core.common.entity;

import lombok.Getter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @NaturalId
    protected Long id;
}
