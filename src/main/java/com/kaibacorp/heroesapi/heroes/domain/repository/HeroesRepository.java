package com.kaibacorp.heroesapi.heroes.domain.repository;

import com.kaibacorp.heroesapi.heroes.domain.document.Hero;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroesRepository extends CrudRepository<Hero,String> {
}
