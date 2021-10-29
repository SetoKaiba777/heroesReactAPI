package com.kaibacorp.heroesapi.heroes.API.heroDTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeroDTO {
    private String name;

    private String universe;

    private int movies ;

    public HeroDTO(String name, String universe, int movies){
        this.name=name;
        this.universe=universe;
        this.movies=movies;
    }
}
