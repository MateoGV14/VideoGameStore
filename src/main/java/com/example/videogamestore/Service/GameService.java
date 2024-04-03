package com.example.videogamestore.Service;


import com.example.videogamestore.Model.Game;
import com.example.videogamestore.Repository.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public Iterable<Game> getGames(){
        return gameRepository.findAll();
    }

    public Game addGame(Game game){
        return gameRepository.save(game);
    }

    public Optional<Game> findGameById(Long id) {
      return gameRepository.findById(id);

    }

    @Transactional
    public Game updateGame(Long id, Game game){

         Game newGame= gameRepository.findById(id).orElseThrow(EntityNotFoundException::new);

         newGame.setDescription(game.getDescription());
         newGame.setGenres(game.getGenres());
         newGame.setTitle(game.getTitle());
         newGame.setPrice(game.getPrice());

         return gameRepository.save(newGame);
    }

    @Transactional
    public void deleteGameById(Long id){

        Game game = gameRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        gameRepository.delete(game);
    }
}
