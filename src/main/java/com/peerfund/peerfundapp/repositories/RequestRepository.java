package com.peerfund.peerfundapp.repositories;

import com.peerfund.peerfundapp.entities.Request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    Request findRequestById(Long id);

    Request findRequestByIdAndContributionGroup_Id(Long id, Long contributionGroup_id);
}
