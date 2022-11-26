package com.peerfund.peerfundapp.repositories;

import com.peerfund.peerfundapp.entities.ContributionGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributionGroupRepository extends JpaRepository<ContributionGroup, Long> {

    boolean existsContributionGroupByName(String name);

    ContributionGroup findContributionGroupByIdAndAdmin_Id(Long id, Long admin_id);

    List<ContributionGroup> findContributionGroupsByAdmin_Id(Long admin_id);

    ContributionGroup findContributionGroupById(Long id);
}
