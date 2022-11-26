package com.peerfund.peerfundapp.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "contribution_group")
public class ContributionGroup extends BaseEntity{

//    @Length(min = 3,max = 20,message = "title length between 3 and 20")
   @Column(name = "group_name",nullable = false,unique = true)
    private String name;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "payment_start_date")
    private LocalDateTime paymentStartDate;

    @Column(name = "payment_end_date")
    private LocalDateTime paymentEndDate;

    @Column(name = "Slots")
    private int slots;
//
//    @Length(min = 3,max = 20,message = "title length between 3 and 20")
//    @Column(name = "title",nullable = false)
    private String purpose_title;

    @Column(name = "message",nullable = false)
    private String description;


    @Column(name = "amount",nullable = false)
    private Double group_amount;

    @OneToMany(mappedBy = "contributionGroup")
    private List<Contribution> contributions;

    @OneToMany(mappedBy = "contributionGroup")
    private List<Request> requests;

    @OneToMany(mappedBy = "contributionGroup")
    private List<User> members;

    @OneToMany(mappedBy = "contributionGroup")
    @Column(name = "comments")
    private List<Comment> comments;

    @OneToOne
    @JoinColumn(name = "group_admin")
    private User admin;

    public ContributionGroup(String name, int slots, String purpose_title, String description, Double group_amount, User admin) {
        this.name = name;
        this.slots = slots;
        this.purpose_title = purpose_title;
        this.description = description;
        this.group_amount = group_amount;
        this.admin = admin;
    }

    public ContributionGroup(String name, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime paymentStartDate, LocalDateTime paymentEndDate, int slots, String purpose_title, String description, Double group_amount) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentStartDate = paymentStartDate;
        this.paymentEndDate = paymentEndDate;
        this.slots = slots;
        this.purpose_title = purpose_title;
        this.description = description;
        this.group_amount = group_amount;
    }
}
