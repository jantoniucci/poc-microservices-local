package poc.raml.accounts.transfers.dtos;

import lombok.Data;

/**
 * Created by frascuchon on 13/11/15.
 */
@Data
public class DeleteTransferDTO {


	private String transferId;
	private String accountId;

	public DeleteTransferDTO(String accountId, String transferId) {
		this.accountId = accountId;
		this.transferId = transferId;
	}
}
