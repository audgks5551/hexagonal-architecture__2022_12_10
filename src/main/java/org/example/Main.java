package org.example;

import org.example.domain.vo.IP;
import org.example.domain.vo.Network;
import org.example.domain.vo.RouterId;
import org.example.framework.adapter.input.RouterNetworkCLIAdapter;

public class Main {
     public static void main(String[] args) {
        var cli = new RouterNetworkCLIAdapter();
        var routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
        var network = new Network(new IP("20.0.0.0"), "Marketing", 8);
        var router = cli.addNetwork(routerId, network);
        System.out.println(router);
    }
}