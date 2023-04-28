package com.api.anime.client;

import com.api.anime.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

//FAZER REQUISIÇÃO PARA OUTRA URL EXTERNA
@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate()
                .getForEntity("http://localhost:8080/anime/{id}", Anime.class, 2);
        log.info(entity);

        Anime obj = new RestTemplate()
                .getForObject("http://localhost:8080/anime/{id}", Anime.class, 2);
        log.info(obj);

        Anime[] animes = new RestTemplate()
                .getForObject("http://localhost:8080/anime/all", Anime[].class);
        log.info(Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchange = new RestTemplate()
                .exchange(
                        "http://localhost:8080/anime/all",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Anime>>() {
                        });
        log.info(exchange.getBody());

        Anime hxh = Anime.builder().name("Hunter x Hunter").build();
        ResponseEntity<Anime> hxhSaved = new RestTemplate()
                .exchange(
                        "http://localhost:8080/anime/add",
                        HttpMethod.POST,
                        new HttpEntity<>(hxh, createJsonHeader()),
                        Anime.class);
        log.info("saved anime {}", hxhSaved);

        Anime animeToBeUpdated = hxhSaved.getBody();
        animeToBeUpdated.setName("Hunter x Hunter 2");
        ResponseEntity<Void> hxhUpdated = new RestTemplate()
                .exchange(
                        "http://localhost:8080/anime",
                        HttpMethod.PUT,
                        new HttpEntity<>(animeToBeUpdated, createJsonHeader()),
                        Void.class);
        log.info(hxhUpdated);

        ResponseEntity<Void> hxhDeleted = new RestTemplate()
                .exchange(
                        "http://localhost:8080/anime/{id}",
                        HttpMethod.DELETE,
                        null,
                        Void.class,
                        animeToBeUpdated.getId());
        log.info(hxhDeleted);
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
