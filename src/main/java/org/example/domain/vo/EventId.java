package org.example.domain.vo;

import java.util.UUID;

public class EventId {
    private final UUID id;

    private EventId(UUID id) {
        this.id = id;
    }

    public static EventId of(String id) {
        return new EventId(UUID.fromString(id));
    }
}
