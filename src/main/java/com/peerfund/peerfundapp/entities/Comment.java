package com.peerfund.peerfundapp.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "Comments")
public class Comment extends BaseEntity{

   @Column(name = "message",nullable = false)
    private String message;

   @Column(name = "date_of_comment")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private User member;

    @ManyToOne
    @JoinColumn(name = "contribution_group_id")
    private ContributionGroup contributionGroup;
}
