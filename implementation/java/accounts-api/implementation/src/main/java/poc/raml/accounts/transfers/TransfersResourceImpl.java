package poc.raml.accounts.transfers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import poc.raml.accounts.security.SessionManager;
import poc.raml.accounts.transfers.dtos.DeleteTransferDTO;
import poc.raml.accounts.transfers.dtos.GetAccountTransferDTO;
import poc.raml.accounts.transfers.dtos.RegisterTransferChangeDTO;
import poc.raml.accounts.transfers.mappers.TransfersMapper;
import poc.raml.accounts.transfers.services.TransfersService;
import poc.raml.jaxrs.accounts.model.ErrorMessage;
import poc.raml.jaxrs.accounts.model.ResponseMessage;
import poc.raml.jaxrs.accounts.model.Transfer;
import poc.raml.jaxrs.accounts.model.Transfers;
import poc.raml.jaxrs.accounts.resource.TransfersResource;

import java.util.List;

/**
 * Created by frascuchon on 13/11/15.
 */
@Component
public class TransfersResourceImpl implements TransfersResource {

	private final TransfersService transfersService;
	private final SessionManager sessionManager;

	@Autowired
	public TransfersResourceImpl(TransfersService transfersService, SessionManager sessionManager) {
		this.transfersService = transfersService;
		this.sessionManager = sessionManager;
	}

	@Override
	public GetTransfersResponse getTransfers(
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
		return GetTransfersResponse.withJsonOK(transfers);
	}

	@Override
	public GetTransfersByTransferIdResponse getTransfersByTransferId(
					String transferId,
					String accessToken
	) throws Exception {

		Transfer accontTransfer = transfersService
						.getAccountTransferId(
										new GetAccountTransferDTO(sessionManager.getCurrentAccountId(accessToken), transferId));

		if (accontTransfer == null) {
			return GetTransfersByTransferIdResponse.withJsonNotFound(new ErrorMessage().withMessage("No transfer found"));
		} else {
			return GetTransfersByTransferIdResponse.withJsonOK(accontTransfer);
		}
	}

	@Override
	public PutTransfersByTransferIdResponse putTransfersByTransferId(
					String transferId,
					String accessToken,
					Transfer transfer
	) throws Exception {

		RegisterTransferChangeDTO registerTransferChangeDTO = TransfersMapper.mapToTransferDto(transferId, transfer);
		transfersService.registTransferChange(registerTransferChangeDTO);

		return PutTransfersByTransferIdResponse.withJsonOK(transfer);
	}


	@Override
	public DeleteTransfersByTransferIdResponse deleteTransfersByTransferId(
					String transferId,
					String accessToken
	) throws Exception {

		transfersService.deleteTransfer(new DeleteTransferDTO(sessionManager.getCurrentAccountId(accessToken), transferId));
		return DeleteTransfersByTransferIdResponse.withJsonOK(new ResponseMessage().withMessage("OK"));
	}
}
