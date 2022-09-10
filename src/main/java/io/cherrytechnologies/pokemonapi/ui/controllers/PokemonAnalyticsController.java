package io.cherrytechnologies.pokemonapi.ui.controllers;

import io.cherrytechnologies.pokemonapi.services.PokemonAnalyticsService;
import io.cherrytechnologies.pokemonapi.ui.controllers.models.response.MessageResponse;
import io.cherrytechnologies.pokemonapi.ui.controllers.models.response.PokemonDescription;
import io.cherrytechnologies.pokemonapi.utils.dataclasses.PokemonCategoriesBy;
import io.cherrytechnologies.pokemonapi.utils.dataclasses.PokemonMaxDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

import static io.cherrytechnologies.pokemonapi.utils.GenericLogger.getLogger;

@RestController
@RequestMapping(path = "/v1/api/pokemons/analytics")
public class PokemonAnalyticsController {

    @Autowired
    public PokemonAnalyticsService service;
    private final String className = String.valueOf(PokemonAnalyticsController.class);

    @GetMapping(path = "/heaviest",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<MessageResponse<PokemonDescription>> getHeaviestPokemon() {
        getLogger(className).get().info("GET /heaviest");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service
                        .getHeaviestPokemon());
    }

    @GetMapping(path = "/maxed-pokemons",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse<Map<String, PokemonMaxDescription>>> getMaxedOutPokemon(){
        return ResponseEntity
                .ok(service.getMaxPokemonsForEachAttribute());
    }

    @GetMapping(path = "/by-categories")
    public ResponseEntity<MessageResponse<Map<String,Collection<PokemonCategoriesBy>>>> getByCategories(){
        return ResponseEntity
                .ok(service.getPokemonByCategories());
    }
}
