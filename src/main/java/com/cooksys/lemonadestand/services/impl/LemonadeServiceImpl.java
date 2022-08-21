package com.cooksys.lemonadestand.services.impl;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.exceptions.BadRequestException;
import com.cooksys.lemonadestand.exceptions.NotFoundException;
import com.cooksys.lemonadestand.mappers.LemonadeMapper;
import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.LemonadeResponseDto;
import com.cooksys.lemonadestand.repositories.LemonadeRepository;
import com.cooksys.lemonadestand.services.LemonadeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LemonadeServiceImpl implements LemonadeService {

    private LemonadeRepository lemonadeRepository;
    private LemonadeMapper lemonadeMapper;

    @Override
    public List<LemonadeResponseDto> getAllLemonades() {
        return lemonadeMapper.entitiesToResponseDtos(lemonadeRepository.findAll());
    }

    public void setLemonadePrice(Lemonade lemonade) {
        lemonade.setPrice(
                lemonade.getLemonJuice() * 0.2 +
                        lemonade.getWater() * 0.01 +
                        lemonade.getSugar() * 0.15 +
                        lemonade.getIceCubes() * 0.05 + 0.5);
    }

    private void validateLemonadeRequest(LemonadeRequestDto lemonadeRequestDto) {
        if (
                lemonadeRequestDto.getLemonJuice() == null ||
                        lemonadeRequestDto.getWater() == null ||
                        lemonadeRequestDto.getSugar() == null ||
                        lemonadeRequestDto.getIceCubes() == null
        ) throw new BadRequestException("All fields are required on a lemonadeRequestDto.");
    }

    private Lemonade getLemonade(Long id) {
        Optional<Lemonade> optionalLemonade = lemonadeRepository.findById(id);
        if (optionalLemonade.isEmpty())
            throw new NotFoundException("Lemonade with id: " + id + " not found.");
        return optionalLemonade.get();
    }

    @Override
    public LemonadeResponseDto createLemonade(LemonadeRequestDto lemonadeRequestDto) {
        validateLemonadeRequest(lemonadeRequestDto);

        // MAP THE REQUEST DTO TO A LEMONADE ENTITY
        Lemonade lemonadeToSave = lemonadeMapper.requestDtoToEntity(lemonadeRequestDto);
        setLemonadePrice(lemonadeToSave);

        // SAVE THE NEW LEMONADE ENTITY
        // SET AND RETURN THE RETURN DATA WITH THE GENERATED ID AND CALCULATED PRICE
        return lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonadeToSave));
    }

    @Override
    public LemonadeResponseDto getLemonadeById(Long id) {
        return lemonadeMapper.entityToResponseDto(getLemonade(id));
    }

    @Override
    public LemonadeResponseDto updateLemonade(Long id, LemonadeRequestDto lemonadeRequestDto) {

        validateLemonadeRequest(lemonadeRequestDto);

        Lemonade lemonadeToUpdate = getLemonade(id);

        lemonadeToUpdate.setIceCubes(lemonadeRequestDto.getIceCubes());
        lemonadeToUpdate.setLemonJuice(lemonadeRequestDto.getLemonJuice());
        lemonadeToUpdate.setWater(lemonadeRequestDto.getWater());
        lemonadeToUpdate.setSugar(lemonadeRequestDto.getSugar());
        setLemonadePrice(lemonadeToUpdate);

        return lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonadeToUpdate));
    }
}
