package org.example.domain.entity;

import org.example.domain.policy.RegexEventParser;
import org.example.domain.policy.SplitEventParser;
import org.example.domain.vo.Activity;
import org.example.domain.vo.EventId;
import org.example.domain.vo.ParsePolicyType;
import org.example.domain.vo.Protocol;

import java.time.OffsetDateTime;

public class Event implements Comparable<Event> {
    private OffsetDateTime timestamp;
    private EventId id;
    private Protocol protocol;
    private Activity activity;

    @Override
    public int compareTo(Event event) {
        return timestamp.compareTo(event.timestamp);
    }

    public Event(OffsetDateTime timestamp, EventId id, Protocol protocol, Activity activity) {
        this.timestamp = timestamp;
        this.id = id;
        this.protocol = protocol;
        this.activity = activity;
    }

    public static Event parsedEvent(String unparsedEvent, ParsePolicyType policy) {
        return switch (policy) {
            case REGEX -> new RegexEventParser().parseEvent(unparsedEvent);
            case SPLIT -> new SplitEventParser().parseEvent(unparsedEvent);
            default -> throw new IllegalArgumentException("Invalid policy");
        };
    }
}
