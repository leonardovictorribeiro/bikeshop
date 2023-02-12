package dev.leonardovictor.bikeshop.exception;

public class ToolNotFoundException extends RuntimeException {
    public ToolNotFoundException(long id) {
        super(String.format("No tool with id %s is available",id));
    }
}
