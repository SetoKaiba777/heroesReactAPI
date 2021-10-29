package com.kaibacorp.heroesapi.heroes.API.controller;

import static com.kaibacorp.heroesapi.core.constants.HeroesConstants.LOCAL_HEROES_ENDPOINT;

import com.kaibacorp.heroesapi.heroes.API.heroDTO.HeroDTO;
import com.kaibacorp.heroesapi.heroes.API.conversor.ConversionHero;
import com.kaibacorp.heroesapi.heroes.domain.document.Hero;
import com.kaibacorp.heroesapi.heroes.domain.service.HeroesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@AllArgsConstructor
public class HeroController {

    private HeroesService heroesService;
    private ConversionHero conversionHero;

    @GetMapping(LOCAL_HEROES_ENDPOINT)
    public Flux<Hero> getAllItems(){
        log.info("requesting list of all heroes");
        return heroesService.findAll();
    }

    @GetMapping(LOCAL_HEROES_ENDPOINT+"/{id}")
    public Mono<ResponseEntity<Hero>> getAllItems(@PathVariable String id){
        log.info("requesting hero with id {}",id);
        return heroesService.findByIdHero(id).map((item)-> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping(LOCAL_HEROES_ENDPOINT)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Hero> create(@RequestBody Hero hero){
        log.info("New hero was created");
        return heroesService.save(hero);
    }
    @DeleteMapping(LOCAL_HEROES_ENDPOINT+"/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<HttpStatus> delete(@PathVariable String id){
        log.info("Deleting hero with id {}",id);
        heroesService.deletebyIDHero(id);
        return Mono.just(HttpStatus.NO_CONTENT);
    }
    @PutMapping(LOCAL_HEROES_ENDPOINT+"/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Mono<Hero> put(@PathVariable String id,@RequestBody HeroDTO heroDTO) throws Exception {
        var hero = conversionHero.toEntity(heroDTO);
        hero.setId(id);
        return heroesService.putHero(hero);
    }

}
