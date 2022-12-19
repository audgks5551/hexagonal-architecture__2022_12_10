package org.example.application.port.output;

import org.example.domain.entity.Router;
import org.example.domain.vo.RouterId;

public interface RouterNetworkOutputPort {
    Router fetchRouterById(RouterId routerId);
    boolean persistRouter(Router router);
}
