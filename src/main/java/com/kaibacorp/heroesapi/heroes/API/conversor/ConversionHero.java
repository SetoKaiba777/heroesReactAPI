package com.kaibacorp.heroesapi.heroes.API.conversor;

import com.kaibacorp.heroesapi.heroes.API.heroDTO.HeroDTO;
import com.kaibacorp.heroesapi.heroes.domain.document.Hero;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ConversionHero {

    private ModelMapper modelMapper;

    public Hero toEntity(HeroDTO heroDTO) {
        return modelMapper.map(heroDTO, Hero.class);
    }

    public HeroDTO toDTO(Hero hero){
        return modelMapper.map(hero,HeroDTO.class);
    }
}
