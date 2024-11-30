package wzorce.cqrs.web;

import java.time.Instant;

record ErrorResponse(
        String message,
        Instant timestamp) {

    public ErrorResponse(String message) {
        this(message, Instant.now());
    }
}
