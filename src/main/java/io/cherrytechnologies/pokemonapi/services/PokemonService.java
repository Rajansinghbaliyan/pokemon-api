package io.cherrytechnologies.pokemonapi.services;

import io.cherrytechnologies.pokemonapi.io.entity.Pokemon;
import io.cherrytechnologies.pokemonapi.io.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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
                .orElseGet(() -> getPokemonByIdFormNet(id).orElse(null));
    }

    public List<Pokemon> getAll(long start, long end) {
        List<Long> idsInDb = new ArrayList<>((int) (end - start));
        List<Pokemon> pokemonList = repository
                .findAllByIdBetween(start, end);

        pokemonList
                .forEach(pokemon -> idsInDb.add(pokemon.id));

        IntStream.range((int) start, (int) end + 1)
                .parallel()
                .filter(number -> !idsInDb.contains(number))
                .mapToObj(this::getPokemonByIdFormNet)
                .forEach(optionalPokemon ->
                        optionalPokemon
                                .ifPresent(pokemon ->
                                        pokemonList.add((int) getCorrectIndex(start, pokemon.id), pokemon))
                );

        getLogger(className).get().info(String.format("Ids found in Db: %s",idsInDb.toString()));

        return pokemonList;
    }

    public Pokemon save(Pokemon pokemon) {
        getLogger(className).get().info("Saving to DB ID:" + pokemon.getId());
        return repository
                .save(pokemon);
    }

    private Optional<Pokemon> getPokemonByIdFormNet(long id) {
        String url = urlBuilder(BASE_URL, id);
        getLogger(className).get().info("Sending GET url = " + url);

        Optional<Pokemon> optionalPokemon = getFromNet(url);

        optionalPokemon.ifPresent(this::save);

        return optionalPokemon;
    }

    private Optional<Pokemon> getFromNet(String url) {
        try {
            Pokemon pokemon = template.getForObject(url, Pokemon.class);
            return Optional.of(pokemon);
        } catch (RestClientException e) {
            getLogger(className).get().error(e.getMessage());
            return Optional.empty();
        }
    }


    private static long getCorrectIndex(long startId, long currentId) {
        return currentId - startId;
    }
}
