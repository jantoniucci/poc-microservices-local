package poc.raml.accounts.transfers.services;

import poc.raml.accounts.transfers.dtos.DeleteTransferDTO;
import poc.raml.accounts.transfers.dtos.GetAccountTransferDTO;
import poc.raml.accounts.transfers.dtos.RegisterTransferChangeDTO;
import poc.raml.jaxrs.accounts.model.Transfer;

import java.util.List;

/**
 * Created by frascuchon on 13/11/15.
 */
public interface TransfersService {
	List<Transfer> getAccountsTransfers(String currentAccountId);

	Transfer getAccountTransferId(GetAccountTransferDTO getAccountTransferDTO);

	void registTransferChange(RegisterTransferChangeDTO transferDTO);

	void deleteTransfer(DeleteTransferDTO deleteTransferDTO);
}
