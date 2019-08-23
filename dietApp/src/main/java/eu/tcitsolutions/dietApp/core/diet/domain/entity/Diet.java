package eu.tcitsolutions.dietApp.core.diet.domain.entity;

import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
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

    @OneToOne
    private Client client;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Meal> meals;

    public Diet(){
        this.meals = new HashSet<>();
    }


    public Diet(Long id, Set<Meal> meals, Client client){
        this.id = id;
        this.meals = meals;
        this.client = client;
    }

    public Diet(Set<Meal> meals, Client client){
        this.meals = meals;
        this.client = client;
    }

    public void addMeal(Meal meal){
        meals.add(meal);
    }


}
