package poc.raml.accounts.transfers.dtos;

import lombok.Data;
import poc.raml.jaxrs.accounts.model.Balance;

/**
 * Created by frascuchon on 13/11/15.
 */
@Data
public class BalanceDto  {

	private Double amount;
	private String currencyCode;

	public BalanceDto(Double amount, String currencyCode) {

		this.amount = amount;
		this.currencyCode = currencyCode;
	}
}
