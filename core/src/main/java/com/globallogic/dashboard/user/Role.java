package com.globallogic.dashboard.user;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@SequenceGenerator(name="seqRole", initialValue=1000, allocationSize=1,sequenceName ="seqrole" )
public class Role implements Serializable  , GrantedAuthority {

    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqRole")

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    public Role() {
    }

    public Role(RoleName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name.name();
    }
}
