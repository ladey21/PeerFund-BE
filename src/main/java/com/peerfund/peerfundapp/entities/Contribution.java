package com.peerfund.peerfundapp.entities;

import com.peerfund.peerfundapp.entities.Enums.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "contribution")
public class Contribution extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private User member;

    @ManyToOne
    @JoinColumn(name = "contribution_group_id", referencedColumnName = "id")
    private ContributionGroup contributionGroup;

    @Column(name = "amount_paid")
    private Double amountPaid;

    @Column(name = "payment_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "date_paid")
    private LocalDateTime datePaid;
}

