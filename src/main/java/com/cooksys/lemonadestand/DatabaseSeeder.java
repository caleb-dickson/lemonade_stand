package com.cooksys.lemonadestand;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.repositories.LemonadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private LemonadeRepository lemonadeRepository;

    @Override
    public void run(String... args) throws Exception {
        Lemonade lemonade = new Lemonade();
        lemonade.setLemonJuice(0.3);
        lemonade.setWater(1);
        lemonade.setSugar(0.1);
        lemonade.setIceCubes(5);
        lemonade.setPrice(1.25);

        lemonadeRepository.saveAndFlush(lemonade);
        System.out.println(lemonadeRepository.findAll());
    }
}
