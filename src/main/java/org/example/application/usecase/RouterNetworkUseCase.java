package org.example.application.usecase;

import org.example.domain.entity.Router;
import org.example.domain.vo.Network;
import org.example.domain.vo.RouterId;

public interface RouterNetworkUseCase {
    Router addNetworkToRouter(RouterId routerId, Network network);
}
