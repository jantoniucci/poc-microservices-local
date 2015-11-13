package poc.raml.accounts.transfers.services;

import poc.raml.jaxrs.accounts.model.Transfer;

import java.util.List;

/**
 * Created by frascuchon on 13/11/15.
 */
public interface TransfersService {
	List<Transfer> getAccountsTransfers(String currentAccountId);
}
