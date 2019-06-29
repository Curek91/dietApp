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

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
public class Meal extends BaseLogEntity implements Serializable {

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealProduct> products = new ArrayList<>();

    private int mealNo;

    public Meal(){
    }

    public Meal(int mealNo){
        this.mealNo = mealNo;
    }

    public Meal(Long id, int mealNo){
        this.id = id;
        this.mealNo = mealNo;
    }

    public Meal(Long id, int mealNo, List<MealProduct> products){
        this.id = id;
        this.mealNo = mealNo;
        this.products = products;
    }

    public void addProduct(Product product, int weight){
        MealProduct mealProduct = new MealProduct(this, product, weight);
        products.add(mealProduct);
    }


}
