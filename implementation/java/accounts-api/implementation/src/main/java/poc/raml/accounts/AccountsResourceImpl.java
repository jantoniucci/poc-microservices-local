package poc.raml.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import poc.raml.accounts.security.SessionManager;
import poc.raml.accounts.services.AccountsService;
import poc.raml.accounts.transfers.dtos.DeleteTransferDTO;
import poc.raml.accounts.transfers.dtos.GetAccountTransferDTO;
import poc.raml.accounts.transfers.dtos.RegisterTransferChangeDTO;
import poc.raml.accounts.transfers.mappers.TransfersMapper;
import poc.raml.accounts.transfers.services.TransfersService;
import poc.raml.jaxrs.accounts.model.*;
import poc.raml.jaxrs.accounts.resource.AccountsResource;

import java.util.List;

/**
 * Created by frascuchon on 13/11/15.
 */
@Component
public class AccountsResourceImpl implements AccountsResource {

	private final AccountsService accountsService;
	private final TransfersService transfersService;
	private final SessionManager sessionManager;


	@Autowired
	public AccountsResourceImpl(AccountsService accountsService, TransfersService transfersService, SessionManager sessionManager) {
		this.accountsService = accountsService;
		this.transfersService = transfersService;
		this.sessionManager = sessionManager;
	}

	@Override
	public GetAccountsResponse getAccounts(
					String query,
					String orderBy,
					Order order,
					long offset,
					long limit,
					String accessToken
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
					String accountId,
					String accessToken) throws Exception {

		Account account = accountsService.getAccountById(sessionManager.getCurrentUserId(accessToken), accountId);

		if (account == null) {
			return GetAccountsByAccountIdResponse.withJsonNotFound(new ResponseMessage().withMessage("Account not found"));
		} else {
			return GetAccountsByAccountIdResponse.withJsonOK(account);
		}
	}

	@Override
	public GetAccountsByAccountIdTransfersResponse getAccountsByAccountIdTransfers(
					String accountId,
					String query,
					String orderBy,
					Order order,
					long offset,
					long limit,
					String accessToken
	) throws Exception {


		List<Transfer> accountTransfers = transfersService.getAccountsTransfers(sessionManager.getCurrentAccountId(accessToken));

		Transfers transfers = new Transfers().withSize(0);
		if (!CollectionUtils.isEmpty(accountTransfers)) {
			transfers = new Transfers().withSize(accountTransfers.size()).withTransfers(accountTransfers);
		}
		return GetAccountsByAccountIdTransfersResponse.withJsonOK(transfers);
	}

	@Override
	public GetAccountsByAccountIdTransfersByTransferIdResponse getAccountsByAccountIdTransfersByTransferId(
					String transferId,
					String accountId,
					String accessToken
	) throws Exception {
		Transfer accontTransfer = transfersService
						.getAccountTransferId(
										new GetAccountTransferDTO(accountId, transferId));

		if (accontTransfer == null) {
			return GetAccountsByAccountIdTransfersByTransferIdResponse.withJsonNotFound(new ErrorMessage().withMessage("No transfer found"));
		} else {
			return GetAccountsByAccountIdTransfersByTransferIdResponse.withJsonOK(accontTransfer);
		}
	}

	@Override
	public PutAccountsByAccountIdTransfersByTransferIdResponse putAccountsByAccountIdTransfersByTransferId(
					String transferId,
					String accountId,
					String accessToken,
					Transfer entity
	) throws Exception {

		RegisterTransferChangeDTO registerTransferChangeDTO = TransfersMapper.mapToTransferDto(transferId, entity);

		registerTransferChangeDTO.setAccountIdFrom(accountId);
		transfersService.registTransferChange(registerTransferChangeDTO);

		return PutAccountsByAccountIdTransfersByTransferIdResponse.withJsonOK(entity);
	}

	@Override
	public DeleteAccountsByAccountIdTransfersByTransferIdResponse deleteAccountsByAccountIdTransfersByTransferId(
					String transferId,
					String accountId,
					String accessToken
	) throws Exception {

		transfersService.deleteTransfer(new DeleteTransferDTO(sessionManager.getCurrentAccountId(accessToken), transferId));
		return DeleteAccountsByAccountIdTransfersByTransferIdResponse.withJsonOK(new ResponseMessage().withMessage("OK"));

	}


}
