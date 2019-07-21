package eu.tcitsolutions.dietApp.core.diet.domain.entity;

import eu.tcitsolutions.dietApp.core.common.entity.BaseLogEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
public class Meal extends BaseLogEntity implements Serializable {


    private int mealNo;
    private String suplements;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private Set<MealProduct> mealProducts = new HashSet<>();

    public Meal(){
    }

    public Meal(int mealNo, String suplements, MealProduct... mealProducts) {
        this.mealNo = mealNo;
        this.suplements = suplements;
        for(MealProduct mealProduct : mealProducts) mealProduct.setMeal(this);
        this.mealProducts = Stream.of(mealProducts).collect(Collectors.toSet());
    }

    public void addProduct(Product product, int weight){
        this.mealProducts.add(new MealProduct(this, product, weight));
    }
}
