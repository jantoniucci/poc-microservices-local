package poc.raml.accounts.transfers.mappers;

import poc.raml.accounts.transfers.dtos.BalanceDto;
import poc.raml.accounts.transfers.dtos.RegisterTransferChangeDTO;
import poc.raml.accounts.transfers.dtos.SignerDto;
import poc.raml.jaxrs.accounts.model.Signer;
import poc.raml.jaxrs.accounts.model.Transfer;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by frascuchon on 15/11/15.
 */
public abstract class TransfersMapper {

	public static RegisterTransferChangeDTO mapToTransferDto(@PathParam("transferId") String transferId,Transfer
					transfer) {
		RegisterTransferChangeDTO registerTransferChangeDTO = new RegisterTransferChangeDTO();

		registerTransferChangeDTO.setAccountIdFrom(transferId);
		registerTransferChangeDTO.setAccountIdTo(transfer.getAccountIdTo());
		registerTransferChangeDTO.setAmount(new BalanceDto(transfer.getAmount().getAmount(), transfer.getAmount().getCurrencyCode()));
		registerTransferChangeDTO.setDescription(transfer.getDescription());
		registerTransferChangeDTO.setStatus(transfer.getStatus().toString());
		registerTransferChangeDTO.setSigners(mapSigners(transfer.getSigners()));

		return registerTransferChangeDTO;
	}

	private static List<SignerDto> mapSigners(List<Signer> signers) {
		return signers.stream()
						.map(
										(signer -> new SignerDto(signer.getSignerId(), signer.getSignToken(), signer.getSignType()))
						).collect(Collectors.toList());
	}
}
