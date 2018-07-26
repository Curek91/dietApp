package eu.tcitsolutions.dietApp.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
public class Meal extends BaseLogEntity implements Serializable {

    @OneToMany
    private Set<Product> products;

    public Meal(){
        this.products = new HashSet<>();
    }
    public void addProduct(Product product){
        products.add(product);
    }


}
