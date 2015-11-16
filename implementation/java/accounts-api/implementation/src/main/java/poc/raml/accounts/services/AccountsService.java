package poc.raml.accounts.services;

import poc.raml.jaxrs.accounts.model.Account;

import java.util.List;

/**
 * Created by frascuchon on 13/11/15.
 */
public interface AccountsService {

	List<Account> getCustomerAccounts(String userId);

	Account getAccountById(String userId, String accountId);
}
