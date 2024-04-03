package com.example.videogamestore.Controller;


import com.example.videogamestore.Model.Genre;
import com.example.videogamestore.Service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<Iterable<Genre>> getAllGenres(){
        return ResponseEntity.ok(genreService.getGenres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id){
        return genreService.findGenreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Genre> addGenre(@RequestBody Genre genre, UriComponentsBuilder ucb){
        Genre savedGenre = genreService.addGenre(genre);
        var urlLocation = ucb.path("/api/v1/genres/{id}").buildAndExpand(savedGenre.getId()).toUri();
        return ResponseEntity.created(
                urlLocation
        ).body(savedGenre);
    }
}
