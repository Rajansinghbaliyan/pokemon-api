package io.cherrytechnologies.pokemonapi.ui.controllers;

import io.cherrytechnologies.pokemonapi.io.entity.Pokemon;
import io.cherrytechnologies.pokemonapi.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.cherrytechnologies.pokemonapi.utils.GenericLogger.getLogger;

@RestController
@RequestMapping("/v1/api/pokemon")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    private final String className = String.valueOf(PokemonController.class);

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        pokemonService
                                .getPokemonById(id));
    }

    @GetMapping(path = "/",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<Pokemon>> getAllPokemon(
            @RequestParam(defaultValue = "0") long start,
            @RequestParam(defaultValue = "10") long end) {
        getLogger(className).get().info(String.format("GET / start: %s and end: %s",start,end));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pokemonService
                        .getAll(start, end));

    }
}
