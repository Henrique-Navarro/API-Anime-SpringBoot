package com.api.anime.service;

import com.api.anime.domain.Anime;
import com.api.anime.exception.BadRequestException;
import com.api.anime.mapper.AnimeMapper;
import com.api.anime.repository.AnimeRepository;
import com.api.anime.requests.AnimePostRequestBody;
import com.api.anime.requests.AnimePutRequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//CLASSE RESPONSÁVEL POR IMPLEMENTAR AS REGRAS DE NEGÓCIO
@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public List<Anime> listAllNonPageable() {
        return animeRepository.findAll();
    }

    public Anime findById(Long id) {
        return animeRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void delete(Long id) {
        animeRepository.delete(findById(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findById(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
