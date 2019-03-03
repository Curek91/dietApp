package eu.tcitsolutions.dietApp.security.domain.entity;

import eu.tcitsolutions.dietApp.core.domain.entity.BaseLogEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Authority extends BaseLogEntity implements Serializable {
    private AuthorityName name;

    @ManyToMany
    private List<LoginUser> users;
}
