package org.example.application.port.input;

import org.example.application.port.output.RouterNetworkOutputPort;
import org.example.application.usecase.RouterNetworkUseCase;
import org.example.domain.entity.Router;
import org.example.domain.service.NetworkOperation;
import org.example.domain.vo.Network;
import org.example.domain.vo.RouterId;

public class RouterNetworkInputPort implements RouterNetworkUseCase {
    private final RouterNetworkOutputPort routerNetworkOutputPort;

    public RouterNetworkInputPort(RouterNetworkOutputPort routerNetworkOutputPort) {
        this.routerNetworkOutputPort = routerNetworkOutputPort;
    }

    @Override
    public Router addNetworkToRouter(RouterId routerId, Network network) {
        var router = fetchRouter(routerId);
        return createNetwork(router, network);
    }

    private Router createNetwork(Router router, Network network) {
        var newRouter = NetworkOperation.createNewNetwork(router, network);
        return persistNetwork(router) ? newRouter : router;
    }

    private boolean persistNetwork(Router router) {
        return routerNetworkOutputPort.persistRouter(router);
    }

    private Router fetchRouter(RouterId routerId) {
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }
}
