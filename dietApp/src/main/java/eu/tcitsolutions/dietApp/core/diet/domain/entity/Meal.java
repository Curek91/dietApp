package eu.tcitsolutions.dietApp.core.diet.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@Entity(name = "meal")
@EntityListeners(AuditingEntityListener.class)
public class Meal extends BaseLogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meal_seq_generator")
    @SequenceGenerator(name="meal_seq_generator", sequenceName = "meal_seq", allocationSize=1)
    @NaturalId
    private Long id;

    @Column
    private int mealNo;

    @Column
    private String suplements;

    @ManyToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private Set<MealProduct> mealProducts = new HashSet<>();

    public Meal(){
    }

    public Meal(int mealNo, String suplements, MealProduct... mealProducts) {
        this.mealNo = mealNo;
        this.suplements = suplements;
        for(MealProduct mealProduct : mealProducts) mealProduct.setMeal(this);
        this.mealProducts = Stream.of(mealProducts).collect(Collectors.toSet());
    }

    public Meal(Long id, int mealNo, String suplements, MealProduct... mealProducts) {
        this.id = id;
        this.mealNo = mealNo;
        this.suplements = suplements;
        for(MealProduct mealProduct : mealProducts) mealProduct.setMeal(this);
        this.mealProducts = Stream.of(mealProducts).collect(Collectors.toSet());
    }

    public Meal(int mealNo, String suplements, Set<MealProduct> mealProducts) {
        this.mealNo = mealNo;
        this.suplements = suplements;
        this.mealProducts = mealProducts;
    }

    public void addProduct(Product product, int weight){
        this.mealProducts.add(new MealProduct(this, product, weight));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMealNo() {
        return mealNo;
    }

    public void setMealNo(int mealNo) {
        this.mealNo = mealNo;
    }

    public String getSuplements() {
        return suplements;
    }

    public void setSuplements(String suplements) {
        this.suplements = suplements;
    }

    public Set<MealProduct> getMealProducts() {
        return mealProducts;
    }

    public void setMealProducts(Set<MealProduct> mealProducts) {
        this.mealProducts = mealProducts;
    }
}
