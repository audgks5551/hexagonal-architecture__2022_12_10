package org.example.domain.entity;

import org.example.domain.vo.IP;
import org.example.domain.vo.Network;
import org.example.domain.vo.RouterId;
import org.example.domain.vo.RouterType;

import java.util.List;
import java.util.function.Predicate;

public class Router {

    private final RouterType routerType;
    private final RouterId routerId;
    private Switch networkSwitch;

    public Router(RouterType routerType, RouterId routerId) {
        this.routerType = routerType;
        this.routerId = routerId;
    }

    public Router(RouterType routerType, RouterId routerId, Switch networkSwitch) {
        this.routerType = routerType;
        this.routerId = routerId;
        this.networkSwitch = networkSwitch;
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

    @Override
    public String toString() {
        return "Router{" +
                "routerType=" + routerType +
                ", routerId=" + routerId +
                ", networkSwitch=" + networkSwitch +
                '}';
    }

    public void addNetworkToSwitch(Network network) {
        this.networkSwitch = networkSwitch.addNetwork(network);
    }

    public List<Network> retrieveNetworks() {
        return networkSwitch.getNetworks();
    }

    public Network createNetwork(IP address, String name, int cidr) {
        return new Network(address, name, cidr);
    }
}
