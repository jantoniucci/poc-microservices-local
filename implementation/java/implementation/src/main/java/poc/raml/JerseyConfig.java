package poc.raml;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import poc.raml.impl.AlbumsResourceImpl;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(AlbumsResourceImpl.class);
    }

}