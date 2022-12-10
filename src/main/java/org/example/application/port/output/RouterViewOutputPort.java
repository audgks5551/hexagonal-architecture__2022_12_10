package org.example.application.port.output;

import org.example.domain.entity.Router;

import java.util.List;

public interface RouterViewOutputPort {
    List<Router> fetchRouters();

}
