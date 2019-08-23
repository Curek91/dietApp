package eu.tcitsolutions.dietApp.core.diet.domain.entity;

import eu.tcitsolutions.dietApp.core.common.entity.BaseLogEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "type")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class Type extends BaseLogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_seq_generator")
    @SequenceGenerator(name="type_seq_generator", sequenceName = "type_seq", allocationSize=1)
    @NaturalId
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Type(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Type(String name){
        this.name = name;
    }
}
