package poc.raml.accounts.transfers.services;

import org.springframework.stereotype.Service;
import poc.raml.accounts.transfers.dtos.DeleteTransferDTO;
import poc.raml.accounts.transfers.dtos.GetAccountTransferDTO;
import poc.raml.accounts.transfers.dtos.RegisterTransferChangeDTO;
import poc.raml.jaxrs.accounts.model.Balance;
import poc.raml.jaxrs.accounts.model.Transfer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by frascuchon on 15/11/15.
 */
@Service("mockTransfersService")
public class TransfersServiceMockImpl implements TransfersService {

	@Override
	public List<Transfer> getAccountsTransfers(String currentAccountId) {
		return Arrays.asList(
				mockTransfer()
		);
	}

	private Transfer mockTransfer() {
		return new Transfer()
						.withAccountIdFrom("accountIdFrom")
						.withAccountIdTo("accountIdTo")
						.withAmount(new Balance().withAmount(100.0).withCurrencyCode("EUR"))
						.withDescription("Mock account transfer");
	}

	@Override
	public Transfer getAccountTransferId(GetAccountTransferDTO getAccountTransferDTO) {
		return mockTransfer();
	}

	@Override
	public void registTransferChange(RegisterTransferChangeDTO transferDTO) {
		// nothing to do
	}

	@Override
	public void deleteTransfer(DeleteTransferDTO deleteTransferDTO) {
		// nothing to do
	}
}
