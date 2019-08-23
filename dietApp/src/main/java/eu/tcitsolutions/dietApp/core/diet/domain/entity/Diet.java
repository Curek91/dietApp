package eu.tcitsolutions.dietApp.core.diet.domain.entity;

import eu.tcitsolutions.dietApp.core.common.entity.BaseLogEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name="diet")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Diet extends BaseLogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diet_seq_generator")
    @SequenceGenerator(name="diet_seq_generator", sequenceName = "diet_seq", allocationSize=1)
    @NaturalId
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Meal> meals;

    public Diet(){
        this.meals = new HashSet<>();
    }


    public Diet(Long id, Set<Meal> meals){
        this.id = id;
        this.meals = meals;
    }

    public Diet(Set<Meal> meals){
        this.meals = meals;
    }

    public void addMeal(Meal meal){
        meals.add(meal);
    }


}
