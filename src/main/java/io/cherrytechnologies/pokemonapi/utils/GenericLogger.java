package io.cherrytechnologies.pokemonapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class GenericLogger {
    public static Supplier<Logger> getLogger(String className){
        return () -> LoggerFactory.getLogger(className);
    }
}
