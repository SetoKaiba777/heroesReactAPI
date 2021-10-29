package com.kaibacorp.heroesapi;

import com.kaibacorp.heroesapi.heroes.API.heroDTO.HeroDTO;
import com.kaibacorp.heroesapi.heroes.domain.document.Hero;
import com.kaibacorp.heroesapi.heroes.domain.repository.HeroesRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.kaibacorp.heroesapi.core.constants.HeroesConstants.LOCAL_HEROES_ENDPOINT;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesApiApplicationTests {


	@Autowired
	WebTestClient webTestClient;

	@Autowired
	HeroesRepository heroesRepository;

	@Test
	public void getOneHeroeById(){

		webTestClient.get().uri(LOCAL_HEROES_ENDPOINT.concat("/{id}"),"1")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	public void getOneHeroNotFound(){

		webTestClient.get().uri(LOCAL_HEROES_ENDPOINT.concat("/{id}"),"19")
				.exchange()
				.expectStatus().isNotFound()
				.expectBody();
	}

	@Test
	public void deleteHero(){

		webTestClient.delete().uri(LOCAL_HEROES_ENDPOINT.concat("/{id}"),"1")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNoContent()
				.expectBody(Void.class);

	}
	@Test
	public void deleteNonexistentHero(){

		webTestClient.delete().uri(LOCAL_HEROES_ENDPOINT.concat("/{id}"),"100")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().is5xxServerError()
				.expectBody(Void.class);

	}

	@Test
	public void putHero(){
		var heroDTO = new HeroDTO("Mario","Nintendo",2);
		webTestClient.put().uri(LOCAL_HEROES_ENDPOINT.concat("/{id}"),"2")
				.accept(MediaType.APPLICATION_JSON).
				body(Mono.just(heroDTO),HeroDTO.class).
				exchange().
				expectStatus().
				isOk().
				expectBody();

	}

	@Test
	public void postHero(){
		var hero = new Hero("1","Seiya","Toei Animation",7);
		webTestClient.post().uri(LOCAL_HEROES_ENDPOINT).
				accept(MediaType.APPLICATION_JSON).
				body(Mono.just(hero),Hero.class).
				exchange().
				expectStatus().
				isCreated().
				expectBody();

	}

}
