package com.cooksys.lemonadestand.repositories;

import com.cooksys.lemonadestand.entities.LemonadeStand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LemonadeStandRepository extends JpaRepository<LemonadeStand, Long> {
}
