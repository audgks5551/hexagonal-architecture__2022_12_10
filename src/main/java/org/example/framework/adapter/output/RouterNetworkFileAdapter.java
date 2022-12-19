package org.example.framework.adapter.output;

import org.example.application.port.output.RouterNetworkOutputPort;
import org.example.domain.entity.Router;
import org.example.domain.entity.Switch;
import org.example.domain.vo.*;

import java.util.ArrayList;
import java.util.List;

public class RouterNetworkFileAdapter implements RouterNetworkOutputPort {
    private static RouterNetworkFileAdapter instance;

    private List<Router> routers = new ArrayList<>();

    public RouterNetworkFileAdapter() {
        createSampleRouter();
    }

    public void createSampleRouter() {
        var routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
        var network = new Network(new IP("10.0.0.0"), "HR", 8);
        var networkSwitch = new Switch(SwitchType.LAYER3, SwitchId.withoutId(), List.of(network), new IP("9.0.0.9"));
        var router = new Router(RouterType.EDGE, routerId, networkSwitch);
        routers.add(router);
    }

    public static RouterNetworkFileAdapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkFileAdapter();
        }
        return instance;
    }

    public List<Router> getRouters() {
        return routers;
    }

    @Override
    public Router fetchRouterById(RouterId routerId) {
        Router retrieveRouter = null;
        for(Router router: routers) {
            if(router.getRouterId().getId().equals(routerId.getId())) {
                retrieveRouter = router;
                break;
            }
        }

        return retrieveRouter;
    }

    @Override
    public boolean persistRouter(Router router) {
        return this.routers.add(router);
    }
}
