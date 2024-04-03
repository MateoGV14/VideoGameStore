package com.example.videogamestore.Service;


import com.example.videogamestore.Model.Genre;
import com.example.videogamestore.Repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService{
    private final GenreRepository genreRepository;

    public Iterable<Genre> getGenres(){
        return genreRepository.findAll();
    }

    public Genre addGenre(Genre genre){
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre updateGenre(Long id, Genre genre){
        Genre newGenre = genreRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        newGenre.setTitle(genre.getTitle());
        return genreRepository.save(newGenre);
    }

    public Optional<Genre> findGenreById(Long id){
        return genreRepository.findById(id);
    }

    public void deleteGenreById(Long id){

        genreRepository.deleteById(id);
    }
}
