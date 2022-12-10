package org.example.domain.entity;

import org.example.domain.vo.RouterId;
import org.example.domain.vo.RouterType;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Router {

    private final RouterType routerType;
    private final RouterId routerId;

    public Router(RouterType routerType, RouterId routerId) {
        this.routerType = routerType;
        this.routerId = routerId;
    }

    public static Predicate<Router> filterRouterByType(RouterType routerType) {
        return routerType.equals(RouterType.CORE) ? isCore() : isEdge();
    }

    private static Predicate<Router> isEdge() {
        return p -> p.getRouterType() == RouterType.EDGE;
    }

    public RouterId getRouterId() {
        return this.routerId;
    }

    public RouterType getRouterType() {
        return this.routerType;
    }

    private static Predicate<Router> isCore() {
        return p -> p.getRouterType() == RouterType.CORE;
    }

    public static List<Router> retrieveRouter(List<Router> routers, Predicate<Router> predicate) {
        return routers.stream()
                .filter(predicate)
                .collect(Collectors.<Router>toList());
    }

    @Override
    public String toString() {
        return "Router{" +
                "routerType=" + routerType +
                ", routerId=" + routerId +
                '}';
    }
}
