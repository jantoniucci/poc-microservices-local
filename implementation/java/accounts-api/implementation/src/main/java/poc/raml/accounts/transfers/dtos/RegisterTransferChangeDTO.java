package poc.raml.accounts.transfers.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frascuchon on 13/11/15.
 */
@Data
public class RegisterTransferChangeDTO {

	private String accountIdFrom;
	private String accountIdTo;
	private BalanceDto amount;
	private String description;
	private String status;
	private List<SignerDto> signers = new ArrayList<SignerDto>();

}
