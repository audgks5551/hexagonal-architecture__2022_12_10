package org.example.domain.vo;

public class RouterId {
    private final String id;

    public RouterId(String id) {
        this.id = id;
    }

    public static RouterId of(String id) {
        return new RouterId(id);
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RouterId{" +
                "id='" + id + '\'' +
                '}';
    }
}
