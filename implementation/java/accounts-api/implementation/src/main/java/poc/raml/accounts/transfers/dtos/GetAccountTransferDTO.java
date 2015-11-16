package poc.raml.accounts.transfers.dtos;

import lombok.Data;

/**
 * Created by frascuchon on 13/11/15.
 */
public class GetAccountTransferDTO {

	private final String accountId;
	private final String transferId;

	public GetAccountTransferDTO(String currentAccountId, String transferId) {
		this.accountId = currentAccountId;
		this.transferId = transferId;
	}

}
