package poc.raml;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import poc.raml.accounts.AccountsResourceImpl;
import poc.raml.accounts.transfers.TransfersResourceImpl;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(AccountsResourceImpl.class);
        register(TransfersResourceImpl.class);

    }

}