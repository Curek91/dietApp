package eu.tcitsolutions.dietApp.core.common.entity;

import lombok.Getter;
import org.keycloak.KeycloakPrincipal;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Types;
import java.time.LocalDateTime;



@Getter
@MappedSuperclass
public class BaseLogEntity{

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    public String createdBy;

    @LastModifiedBy
    @Column(name = "modified_by")
    public String modifiedBy;

    @CreatedDate
    @Column(name = "creation_timestamp", updatable = false)
    public LocalDateTime creationTime;

    @LastModifiedDate
    @Column(name = "modification_timestamp")
    public LocalDateTime modificationTime;

    @PrePersist
    public void prePersist(){
        this.creationTime = LocalDateTime.now();
        this.createdBy = ((KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getKeycloakSecurityContext().getToken().getPreferredUsername();
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = LocalDateTime.now();
        this.modifiedBy = ((KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getKeycloakSecurityContext().getToken().getPreferredUsername();
    }
}
