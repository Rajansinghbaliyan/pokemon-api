package io.cherrytechnologies.pokemonapi.utils;

import org.springframework.data.domain.PageRequest;

public class RestUtils {
    public static String urlBuilder(String baseUrl, long param) {
        return baseUrl + param;
    }

    public static PageRequest createPageRequest(int start, int size){
        int page = start/size;
        return PageRequest.of(page,size);
    }
}
