package odonto.infra.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.stream.Collectors;

@Configuration
@RestController
public class EndpointListingController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping("/endpoints")
    public Object getAllEndpoints() {
        return requestMappingHandlerMapping.getHandlerMethods().keySet().stream()
                .map(Object::toString)
                .sorted()
                .collect(Collectors.toList());
    }
}
