package poc.raml.accounts.security;

import org.springframework.stereotype.Service;

/**
 * Created by frascuchon on 15/11/15.
 */
@Service("mockSessionManager")
public class SessionManagerMockImpl implements SessionManager {
	@Override
	public String getCurrentUserId(String accessToken) {
		return "userId";
	}

	@Override
	public String getCurrentAccountId(String accessToken) {
		return "accountId";
	}
}
