package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.domain.entity.Router;
import org.example.domain.specification.CIDRSpecification;
import org.example.domain.specification.NetworkAvailabilitySpecification;
import org.example.domain.vo.IP;
import org.example.domain.vo.Network;
import org.example.domain.vo.RouterId;
import org.example.framework.adapter.output.RouterNetworkFileAdapter;

public class AddNetworkSteps {
    RouterId routerId;

    Router router;

    RouterNetworkFileAdapter routerNetworkFileAdapter = RouterNetworkFileAdapter.getInstance();

    Network network = new Network(new IP("20.0.0.0"), "Marketing", 8);

    @Given("I provide a router ID and the network details")
    public void obtain_routerId() {
        this.routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
    }

    @When("I found the router")
    public void lookup_router() {
        router = routerNetworkFileAdapter.fetchRouterById(routerId);
    }

    @And("The network address is valid and doesn't already exists")
    public void check_address_validity_and_existence() {
        var availabilitySpec = new NetworkAvailabilitySpecification(
                network.getAddress(), network.getName(), network.getCidr());

        if (!availabilitySpec.isSatisfiedBy(router)) {
            throw new IllegalArgumentException("Address already exist");
        }
    }

    @And("The CIDR is valid")
    public void check_cidr() {
        var cidrSpec = new CIDRSpecification();
        if(cidrSpec.isSatisfiedBy(network.getCidr())) {
            throw new IllegalArgumentException("CIDR is below" + CIDRSpecification.MINIMUM_ALLOWED_CIDR);
        }
    }

    @Then("Add the network to the router")
    public void add_network() {
        router.addNetworkToSwitch(network);
    }
}
