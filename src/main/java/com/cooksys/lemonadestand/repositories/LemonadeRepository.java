package com.cooksys.lemonadestand.repositories;

import com.cooksys.lemonadestand.entities.Lemonade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LemonadeRepository extends JpaRepository<Lemonade, Long> {
}
