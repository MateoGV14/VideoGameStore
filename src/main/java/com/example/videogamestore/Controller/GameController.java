package com.example.videogamestore.Controller;

import com.example.videogamestore.Model.Game;
import com.example.videogamestore.Repository.GameRepository;
import com.example.videogamestore.Service.GameService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<Iterable<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.getGames());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return gameService.findGameById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody Game game, UriComponentsBuilder ucb) {
        Game savedGame = gameService.addGame(game);
        var urlLocation = ucb.path("/api/v1/games/{id}").buildAndExpand(savedGame.getId()).toUri();
        return ResponseEntity.created(
                urlLocation
        ).body(savedGame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        try {
            Game updatedGame = gameService.updateGame(id, game);
            return ResponseEntity.ok(updatedGame);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGameById(@PathVariable Long id){
        try {
            gameService.deleteGameById(id);
            return ResponseEntity.ok("Game deleted successfully");

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }


}
