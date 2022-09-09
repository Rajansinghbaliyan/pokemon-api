package io.cherrytechnologies.pokemonapi.services;

import io.cherrytechnologies.pokemonapi.io.entity.Pokemon;
import io.cherrytechnologies.pokemonapi.io.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static io.cherrytechnologies.pokemonapi.services.PokemonConstants.BASE_URL;
import static io.cherrytechnologies.pokemonapi.utils.GenericLogger.getLogger;
import static io.cherrytechnologies.pokemonapi.utils.RestUtils.urlBuilder;

@Service
public class PokemonService {

    private final RestTemplate template = new RestTemplate();

    private final String className = String.valueOf(PokemonService.class);
    @Autowired
    private PokemonRepository repository;

    public Pokemon getPokemonById(long id) {
        return repository
                .findById(id)
                .orElseGet(() -> getPokemonByIdFormNet(id));

    }

    public List<Pokemon> getAll(long start, long end) {
        AtomicLong previousId = new AtomicLong(0L);
        List<Long> idsNotInDb = new ArrayList<>();
        List<Optional<Pokemon>> pokemonList = repository
                .findAllByIdBetween(start, end)
                .parallelStream()
                .filter(optionalPokemon -> {
                    if (optionalPokemon.isPresent()) {
                        previousId.set(optionalPokemon.get().id);
                    } else {
                        idsNotInDb.add(previousId.get());
                    }
                    return optionalPokemon.isPresent();
                })
                .collect(Collectors.toList());

        idsNotInDb
                .parallelStream()
                .map(this::getPokemonByIdFormNet)
                .forEach(pokemon -> pokemonList.add(
                        (int) getCorrectIndex(start,pokemon.id),
                        Optional.of(pokemon)
                ));

        return pokemonList
                .stream()
                .map(optionalPokemon -> optionalPokemon.orElse(null))
                .collect(Collectors.toList());
    }

    private Pokemon getPokemonByIdFormNet(long id) {
        String url = urlBuilder(BASE_URL, id);
        getLogger(className).get().info("Sending GET url = " + url);
        Pokemon pokemon = template.getForObject(url, Pokemon.class);

        save(pokemon);

        return pokemon;
    }

    public Pokemon save(Pokemon pokemon) {
        getLogger(className).get().info("Saving to DB ID:" + pokemon.getId());
        return repository
                .save(pokemon);
    }

    private static long getCorrectIndex(long startId, long currentId){
        return  currentId - startId;
    }
}
