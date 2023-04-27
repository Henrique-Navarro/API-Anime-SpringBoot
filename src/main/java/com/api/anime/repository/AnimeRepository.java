package com.api.anime.repository;

import com.api.anime.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//CLASSE QUE REPRESENTA A LIGAÇÃO DIRETA COM O BANCO DE DADOS
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    List<Anime> findByName(String name);
}
