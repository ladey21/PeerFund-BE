package com.peerfund.peerfundapp.entities;

import com.peerfund.peerfundapp.entities.Enums.RequestStatus;
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
@Table(name = "request")
public class Request extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "member_id")
    private User member;

    @Column(name = "date_of_request")
    private LocalDateTime dateOfRequest;

    @Column(name = "date_of_approval")
    private LocalDateTime dateOfRequestApproval;

    @ManyToOne
    @JoinColumn(name = "contribution_group_id")
    private ContributionGroup contributionGroup;

    @Column(name = "status_of_request")
    @Enumerated(value = EnumType.STRING)
    private RequestStatus requestStatus;

    public Request(User member, LocalDateTime dateOfRequest, ContributionGroup contributionGroup, RequestStatus requestStatus) {
        this.member = member;
        this.dateOfRequest = dateOfRequest;
        this.contributionGroup = contributionGroup;
        this.requestStatus = requestStatus;
    }
}
