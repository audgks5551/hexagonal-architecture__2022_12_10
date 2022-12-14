package org.example.domain.service;

import org.example.domain.entity.Router;
import org.example.domain.specification.CIDRSpecification;
import org.example.domain.specification.NetworkAmountSpecification;
import org.example.domain.specification.NetworkAvailabilitySpecification;
import org.example.domain.specification.RouterTypeSpecification;
import org.example.domain.vo.IP;
import org.example.domain.vo.Network;

import static org.example.domain.specification.CIDRSpecification.MINIMUM_ALLOWED_CIDR;

public class NetworkOperation {

    public void createNetwork(Router router, IP address, String name, int cidr) {
        var cidrSpec = new CIDRSpecification();
        var availabilitySpec = new NetworkAvailabilitySpecification(address, name, cidr);
        var amountSpec = new NetworkAmountSpecification();
        var routerTypeSpec = new RouterTypeSpecification();

        if (cidrSpec.isSatisfiedBy(cidr)) {
            throw new IllegalArgumentException("CIDR is below" + MINIMUM_ALLOWED_CIDR);
        }

        if (availabilitySpec.isSatisfiedBy(router)) {
            throw new IllegalArgumentException("Address already exist");
        }

        if (amountSpec.and(routerTypeSpec).isSatisfiedBy(router)) {
            Network network = router.createNetwork(address, name, cidr);
            router.addNetworkToSwitch(network);
        }
    }
}
