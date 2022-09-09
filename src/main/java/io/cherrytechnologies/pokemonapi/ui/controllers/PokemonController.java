package io.cherrytechnologies.pokemonapi.ui.controllers;

import io.cherrytechnologies.pokemonapi.io.entity.Pokemon;
import io.cherrytechnologies.pokemonapi.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/pokemon")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        pokemonService
                                .getPokemonById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Pokemon>> getAllPokemon(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(defaultValue = "10") int end) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pokemonService.getAll(start, end));

    }
}
