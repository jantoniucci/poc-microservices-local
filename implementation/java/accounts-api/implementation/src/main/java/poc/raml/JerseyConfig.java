package poc.raml;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import poc.raml.jaxrs.accounts.resource.AccountsResource;
import poc.raml.jaxrs.accounts.resource.TransfersResource;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

	@Autowired
	public JerseyConfig(
					AccountsResource accountsResource,
					TransfersResource transfersResource
	) {
		register(accountsResource);
		register(transfersResource);

		// Enable validation error details
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
	}

}