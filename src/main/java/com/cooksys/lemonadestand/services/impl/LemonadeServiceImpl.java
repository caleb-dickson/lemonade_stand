package com.cooksys.lemonadestand.services.impl;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.LemonadeResponseDto;
import com.cooksys.lemonadestand.repositories.LemonadeRepository;
import com.cooksys.lemonadestand.services.LemonadeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LemonadeServiceImpl implements LemonadeService {

    private LemonadeRepository lemonadeRepository;

    @Override
    public List<LemonadeResponseDto> getAllLemonades() {
        List<LemonadeResponseDto> result = new ArrayList<>();
        for (Lemonade lemonade : lemonadeRepository.findAll()) {
            result.add(new LemonadeResponseDto(lemonade.getId(), lemonade.getPrice()));
        }
        return result;
    }

    @Override
    public LemonadeResponseDto createLemonade(LemonadeRequestDto lemonadeRequestDto) {
        // MAP THE REQUEST DTO TO A LEMONADE ENTITY
        Lemonade lemonadeToSave = new Lemonade();
        lemonadeToSave.setLemonJuice(lemonadeRequestDto.getLemonJuice());
        lemonadeToSave.setWater(lemonadeRequestDto.getWater());
        lemonadeToSave.setSugar(lemonadeRequestDto.getSugar());
        lemonadeToSave.setIceCubes(lemonadeRequestDto.getIceCubes());
        lemonadeToSave.setPrice(
                lemonadeRequestDto.getLemonJuice() * 0.2 +
                        lemonadeRequestDto.getWater() * 0.01 +
                        lemonadeRequestDto.getSugar() * 0.15 +
                        lemonadeRequestDto.getIceCubes() * 0.05 + 0.5);

        // SAVE THE NEW LEMONADE ENTITY AND STORE THE RESULT IN A VARIABLE
        Lemonade savedLemonade = lemonadeRepository.saveAndFlush(lemonadeToSave);

        // SET THE RETURN DATA WITH THE GENERATED ID AND CALCULATED PRICE
        return new LemonadeResponseDto(savedLemonade.getId(), savedLemonade.getPrice());
    }
}
