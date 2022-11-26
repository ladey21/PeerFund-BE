package com.peerfund.peerfundapp.repositories;

import com.peerfund.peerfundapp.entities.Contribution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {
}
