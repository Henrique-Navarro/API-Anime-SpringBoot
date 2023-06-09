package com.api.anime.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

//CLASSE QUE REPRESENTA A TABELA NO BANCO DE DADOS
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "The anime name cannot be empty")
    private String name;
}
