package com.peerfund.peerfundapp.entities;

import com.peerfund.peerfundapp.entities.Enums.ContributionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PaymentStatus extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "contribution_group_id")
    private ContributionGroup contributionGroup;

    @Enumerated(EnumType.STRING)
    private ContributionStatus contributionStatus;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private User member;

}
