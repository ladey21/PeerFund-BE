package com.peerfund.peerfundapp.entities;

import com.peerfund.peerfundapp.entities.Enums.RoleType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Role(Long id, RoleType roleType) {
        super(id);
        this.roleType = roleType;
    }

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }
}
