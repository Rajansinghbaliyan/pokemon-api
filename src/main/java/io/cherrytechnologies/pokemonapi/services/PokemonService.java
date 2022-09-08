package io.cherrytechnologies.pokemonapi.services;

import io.cherrytechnologies.pokemonapi.entity.Pokemon;
import io.cherrytechnologies.pokemonapi.entity.repository.PokemonRepository;
import io.cherrytechnologies.pokemonapi.utils.GenericLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static io.cherrytechnologies.pokemonapi.services.PokemonConstants.BASE_URL;
import static io.cherrytechnologies.pokemonapi.utils.GenericLogger.getLogger;

@Service
public class PokemonService {

    private final RestTemplate template = new RestTemplate();

    private final String className = String.valueOf(PokemonService.class);
    @Autowired
    private PokemonRepository repository;

    public Pokemon getPokemonById(long id) {
        return repository
                .findById(id)
                .orElseGet(() ->getPokemonByIdFormNet(id));

    }

    private Pokemon getPokemonByIdFormNet(long id) {
        String url = BASE_URL + id;

        getLogger(className).get().info("Sending GET url = " + url);

        Pokemon pokemon = template.getForObject(url, Pokemon.class);

        getLogger(className).get().debug(String.valueOf(pokemon));

        save(pokemon);

        return pokemon;
    }

    public Pokemon save(Pokemon pokemon) {
        getLogger(className).get().info("Saving to DB ID:" + pokemon.getId());
        return repository
                .save(pokemon);
    }
}
