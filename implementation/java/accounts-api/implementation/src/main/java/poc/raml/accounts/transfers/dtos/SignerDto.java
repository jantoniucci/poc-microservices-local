package poc.raml.accounts.transfers.dtos;

import lombok.Data;

/**
 * Created by frascuchon on 13/11/15.
 */
@Data
public class SignerDto {

	private String signerId;
	private String signType;
	private String signToken;

	public SignerDto(String signerId, String signToken, String signType) {
		this.signerId = signerId;
		this.signToken = signToken;
		this.signType = signType;
	}
}
