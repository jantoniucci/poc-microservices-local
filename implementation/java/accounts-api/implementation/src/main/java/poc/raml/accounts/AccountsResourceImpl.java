package poc.raml.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import poc.raml.accounts.data.EmptyAccount;
import poc.raml.accounts.security.SessionManager;
import poc.raml.accounts.services.AccountsService;
import poc.raml.jaxrs.accounts.model.*;
import poc.raml.jaxrs.accounts.resource.AccountsResource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by frascuchon on 13/11/15.
 */
public class AccountsResourceImpl implements AccountsResource {

	private final AccountsService accountsService;
	private final SessionManager sessionManager;

	@Autowired
	public AccountsResourceImpl(AccountsService accountsService, SessionManager sessionManager) {
		this.accountsService = accountsService;
		this.sessionManager = sessionManager;
	}

	@Override
	public GetAccountsResponse getAccounts(
			@QueryParam("query") String query,
			@QueryParam("orderBy") String orderBy,
			@QueryParam("order") @DefaultValue("desc") Order order,
			@QueryParam("offset") @DefaultValue("0") long offset,
			@QueryParam("limit") @DefaultValue("10") long limit,
			@QueryParam("access_token") String accessToken
	) throws Exception {

		String userId = sessionManager.getCurrentUserId(accessToken);
		List<Account> customerAccounts = accountsService.getCustomerAccounts(userId);

		if (CollectionUtils.isEmpty(customerAccounts)) {
			return GetAccountsResponse.withJsonNotFound(new ResponseMessage().withMessage("Accounts not found"));
		} else {
			return GetAccountsResponse.withJsonOK(new Accounts().withSize(customerAccounts.size()).withAccounts
					(customerAccounts));
		}
	}

	@Override
	public GetAccountsByAccountIdResponse getAccountsByAccountId(
			@PathParam("accountId") String accountId,
			@QueryParam("access_token") String accessToken) throws Exception {

		Account account = accountsService.getAccountById(sessionManager.getCurrentUserId(accessToken), accountId);

		if (EmptyAccount.getInstance().equals(account)) {
			return GetAccountsByAccountIdResponse.withJsonNotFound(new ResponseMessage().withMessage("Account not found"));
		} else {
			return GetAccountsByAccountIdResponse.withJsonOK(account);
		}
	}

	@Override
	public GetAccountsByAccountIdTransfersResponse getAccountsByAccountIdTransfers(
			@PathParam("accountId") String accountId,
			@QueryParam("query") String query,
			@QueryParam("orderBy") String orderBy,
			@QueryParam("order") @DefaultValue("desc") Order order,
			@QueryParam("offset") @DefaultValue("0") long offset,
			@QueryParam("limit") @DefaultValue("10") long limit,
			@QueryParam("access_token") String accessToken
	) throws Exception {



		return GetAccountsByAccountIdTransfersResponse.withJsonOK(new Transfers().withSize(1).withTransfers());
	}

	@Override
	public GetAccountsByAccountIdTransfersByTransferIdResponse getAccountsByAccountIdTransfersByTransferId(@PathParam("transferId") String transferId, @PathParam("accountId") String accountId, @QueryParam("access_token") String accessToken) throws Exception {
		return null;
	}

	@Override
	public PutAccountsByAccountIdTransfersByTransferIdResponse putAccountsByAccountIdTransfersByTransferId(@PathParam("transferId") String transferId, @PathParam("accountId") String accountId, @QueryParam("access_token") String accessToken, Transfer entity) throws Exception {
		return null;
	}

	@Override
	public DeleteAccountsByAccountIdTransfersByTransferIdResponse deleteAccountsByAccountIdTransfersByTransferId(@PathParam("transferId") String transferId, @PathParam("accountId") String accountId, @QueryParam("access_token") String accessToken) throws Exception {
		return null;
	}
}
