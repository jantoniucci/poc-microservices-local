package poc.raml.accounts.security;

/**
 * Created by frascuchon on 13/11/15.
 */
public interface SessionManager {

	String getCurrentUserId(String accessToken);

	String getCurrentAccountId(String accessToken);
}
