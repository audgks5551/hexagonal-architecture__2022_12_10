package org.example.framework.adapter.input;

import org.example.application.port.input.RouterNetworkInputPort;
import org.example.application.usecase.RouterNetworkUseCase;
import org.example.domain.entity.Router;
import org.example.domain.vo.Network;
import org.example.domain.vo.RouterId;
import org.example.framework.adapter.output.RouterNetworkFileAdapter;

public class RouterNetworkCLIAdapter {
    RouterNetworkUseCase routerNetworkUseCase;

    public RouterNetworkCLIAdapter(){
        setAdapters();
    }

    public Router addNetwork(RouterId routerId, Network network){
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    private void setAdapters(){
        this.routerNetworkUseCase = new RouterNetworkInputPort(RouterNetworkFileAdapter.getInstance());
    }
}
