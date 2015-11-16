package poc.raml.accounts.services;

import org.springframework.stereotype.Service;
import poc.raml.jaxrs.accounts.model.Account;
import poc.raml.jaxrs.accounts.model.Balance;

import java.util.Arrays;
import java.util.List;

/**
 * Created by frascuchon on 15/11/15.
 */
@Service("mockAccountsService")
public class AccountsServiceMockImpl implements AccountsService {

	@Override
	public List<Account> getCustomerAccounts(String userId) {
		return Arrays.asList(
						mockAccount()
		);
	}

	private Account mockAccount() {
		return new Account()
						.withAccountId("accountId")
						.withAlias("alias")
						.withBalance(new Balance().withAmount(10000.0).withCurrencyCode("EUR"))
						.withDescription("Mock account")
						.withIBAN("IBAN");
	}

	@Override
	public Account getAccountById(String userId, String accountId) {
		return mockAccount();
	}
}
