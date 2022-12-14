package org.example.domain.specification;

import org.example.domain.entity.Router;
import org.example.domain.vo.RouterType;

public class RouterTypeSpecification extends AbstractSpecification<Router> {
    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.getRouterType().equals(RouterType.EDGE) ||
                router.getRouterType().equals(RouterType.CORE);
    }
}
