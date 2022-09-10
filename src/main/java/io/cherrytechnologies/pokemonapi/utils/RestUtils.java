package io.cherrytechnologies.pokemonapi.utils;

import org.springframework.data.domain.PageRequest;

public class RestUtils {

    public static String BASE_URL_POKEMON = "http://localhost:8081/v1/api/pokemon";
    public static String urlBuilder(String baseUrl, long param) {
        return baseUrl + param;
    }

    public static PageRequest createPageRequest(int start, int size){
        int page = start/size;
        return PageRequest.of(page,size);
    }

    public static String  getBasePokemonUrl(long id){
        return String.format("%s/%d",BASE_URL_POKEMON,id);
    }
}
