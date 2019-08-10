package eu.tcitsolutions.dietApp.core.diet.domain.entity;

import eu.tcitsolutions.dietApp.core.common.entity.BaseLogEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name="diet")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
public class Diet extends BaseLogEntity implements Serializable {

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Meal> meals;

    public Diet(){
        this.meals = new HashSet<>();
    }


    public Diet(Long id, Set<Meal> meals){
        this.id = id;
        this.meals = meals;
    }

    public void addMeal(Meal meal){
        meals.add(meal);
    }


}
