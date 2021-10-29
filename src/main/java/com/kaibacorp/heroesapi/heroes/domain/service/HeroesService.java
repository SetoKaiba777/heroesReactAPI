package com.kaibacorp.heroesapi.heroes.domain.service;

import com.kaibacorp.heroesapi.heroes.domain.document.Hero;
import com.kaibacorp.heroesapi.heroes.domain.exception.DontFoundEntityException;
import com.kaibacorp.heroesapi.heroes.domain.repository.HeroesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
@AllArgsConstructor
public class HeroesService {

    private HeroesRepository heroesRepository;

    public Flux<Hero> findAll(){
        return Flux.fromIterable(heroesRepository.findAll());
    }

    public  Mono<Hero> findByIdHero(String id){
        return  Mono.justOrEmpty(heroesRepository.findById(id));
    }

    public Mono<Hero> save(Hero heroes){
        return  Mono.justOrEmpty(heroesRepository.save(heroes));
    }


    public Mono<Boolean> deletebyIDHero(String id) {
        heroesRepository.deleteById(id);
        return Mono.just(true);
    }

    public Mono<Hero> putHero(Hero hero) throws Exception{
        var databaseHero = heroIdExisits(hero);
        return Mono.
                just(heroesRepository.
                save(setStatus(databaseHero,hero))
                );
    }

    private Hero heroIdExisits(Hero hero){
        var databaseHero = heroesRepository.findById(hero.getId());
        return databaseHero.
                orElseThrow(()-> new DontFoundEntityException("Don't found this hero"));
    }

    private Hero setStatus(Hero hero, Hero newHero){
        hero.setId(newHero.getId());
        hero.setName(newHero.getName());
        hero.setUniverse(newHero.getUniverse());
        hero.setMovies(newHero.getMovies());
        return hero;

    }

}
