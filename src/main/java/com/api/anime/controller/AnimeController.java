package com.api.anime.controller;

import com.api.anime.domain.Anime;
import com.api.anime.requests.AnimePostRequestBody;
import com.api.anime.requests.AnimePutRequestBody;
import com.api.anime.service.AnimeService;
import com.api.anime.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//CLASSE RESPONSÁVEL PELOS END POINTS
@RestController                  /*RETORNA JSON*/
@RequestMapping("anime")         /*CONTEXTO /anime*/
@Log4j2                          /*IMPRIMIR NO CONSOLE*/
@RequiredArgsConstructor         /*CONSTRUTOR COM ARGUMENTOS OBRIGATÓRIOS + ATRIBUTOS FINAL = INJEÇÃO DE DEPENDÊNCIA*/
public class AnimeController {
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Anime>> get_list() {
        log.info("Anime buscado às: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable Long id) {
        return new ResponseEntity<>(animeService.findById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Anime> save(@RequestBody AnimePostRequestBody animePostRequestBody) {
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
}
