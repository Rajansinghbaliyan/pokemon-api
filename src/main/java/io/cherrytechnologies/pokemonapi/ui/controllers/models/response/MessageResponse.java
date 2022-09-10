package io.cherrytechnologies.pokemonapi.ui.controllers.models.response;

public class MessageResponse<T> {
    private String message;
    private T data;

    public MessageResponse<T> messageResponseBuilder(){
        return new MessageResponse<>();
    }

    public String getMessage() {
        return message;
    }

    public MessageResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public MessageResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
