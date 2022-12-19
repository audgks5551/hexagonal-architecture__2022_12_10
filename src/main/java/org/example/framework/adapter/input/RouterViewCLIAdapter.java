package org.example.framework.adapter.input;

import org.example.application.port.input.RouterViewInputPort;
import org.example.application.usecase.RouterViewUseCase;
import org.example.domain.entity.Router;
import org.example.domain.vo.RouterType;
import org.example.framework.adapter.output.RouterViewFileAdapter;

import java.util.List;

public class RouterViewCLIAdapter {

    RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter() {
        setAdapters();
    }

    private void setAdapters() {
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(Router.filterRouterByType(RouterType.valueOf(type)));
    }
}
