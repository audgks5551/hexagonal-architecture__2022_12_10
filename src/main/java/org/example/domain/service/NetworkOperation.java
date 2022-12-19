package org.example.domain.service;

import org.example.domain.entity.Router;
import org.example.domain.specification.CIDRSpecification;
import org.example.domain.specification.NetworkAmountSpecification;
import org.example.domain.specification.NetworkAvailabilitySpecification;
import org.example.domain.specification.RouterTypeSpecification;
import org.example.domain.vo.Network;

import static org.example.domain.specification.CIDRSpecification.MINIMUM_ALLOWED_CIDR;

public class NetworkOperation {

    public static Router createNewNetwork(Router router, Network network) {
        var cidrSpec = new CIDRSpecification();
        var availabilitySpec = new NetworkAvailabilitySpecification(network.getAddress(), network.getName(), network.getCidr());
        var amountSpec = new NetworkAmountSpecification();
        var routerTypeSpec = new RouterTypeSpecification();

        if (cidrSpec.isSatisfiedBy(network.getCidr())) {
            throw new IllegalArgumentException("CIDR is below" + MINIMUM_ALLOWED_CIDR);
        }

        if (!availabilitySpec.isSatisfiedBy(router)) {
            throw new IllegalArgumentException("Address already exist");
        }

        if (amountSpec.and(routerTypeSpec).isSatisfiedBy(router)) {
            Network newNetwork = router.createNetwork(network.getAddress(), network.getName(), network.getCidr());
            router.addNetworkToSwitch(network);
        }

        return router;
    }
}
