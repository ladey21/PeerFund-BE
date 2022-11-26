package com.peerfund.peerfundapp.entities;

import com.peerfund.peerfundapp.entities.Enums.DeliveryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity{

//   @NotBlank(message = "first name can not be blank")
//   @Column(name = "first_name", nullable = false)
   public String firstName;

//   @NotBlank(message = "last name can not be blank")
//   @Column(name = "last_name",nullable = false)
   public String lastName;

//   @Size(min = 6, message="password length must be more than 5")
//   @Column(name = "password", nullable = false)
   public String password;

//   @Email(message = "email must have valid format")
//   @Column(name = "email_address", nullable = false, unique = true)
    public String email;

    @Column(name = "phone_number")
    private String phoneNumber;


   @ManyToOne
   @JoinColumn(name = "contribution_group_id",referencedColumnName = "id")
   private ContributionGroup contributionGroup;


    @OneToMany(mappedBy = "member")
    private List<Contribution> contributions;


    @OneToMany(mappedBy = "member")
    private List<Request> requests;

    @Column(name = "delivery_type")
    @Enumerated(value = EnumType.STRING)
    private DeliveryType deliveryType;

    @OneToMany
    @Column(name = "comment")
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles;

    public User(Long id, String firstName, String lastName, String password, String email, String phoneNumber, Set<Role> roles) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }
}
