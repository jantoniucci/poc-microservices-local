package poc.raml.accounts.transfers;

import org.springframework.util.CollectionUtils;
import poc.raml.accounts.security.SessionManager;
import poc.raml.accounts.transfers.services.TransfersService;
import poc.raml.jaxrs.accounts.model.Transfer;
import poc.raml.jaxrs.accounts.model.Transfers;
import poc.raml.jaxrs.accounts.resource.TransfersResource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by frascuchon on 13/11/15.
 */
public class TransfersResourceImpl implements TransfersResource {

	private final TransfersService transfersService;
	private final SessionManager sessionManager;

	public TransfersResourceImpl(TransfersService transfersService, SessionManager sessionManager) {
		this.transfersService = transfersService;
		this.sessionManager = sessionManager;
	}

	@Override
	public GetTransfersResponse getTransfers(
					@QueryParam("query") String query,
					@QueryParam("orderBy") String orderBy,
					@QueryParam("order") @DefaultValue("desc") Order order,
					@QueryParam("offset") @DefaultValue("0") long offset,
					@QueryParam("limit") @DefaultValue("10") long limit,
					@QueryParam("access_token") String accessToken
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
					@PathParam("transferId") String transferId,
					@QueryParam("access_token") String accessToken
	) throws Exception {

		return null;
	}

	@Override
	public PutTransfersByTransferIdResponse putTransfersByTransferId(@PathParam("transferId") String transferId, @QueryParam("access_token") String accessToken, Transfer entity) throws Exception {
		return null;
	}

	@Override
	public DeleteTransfersByTransferIdResponse deleteTransfersByTransferId(@PathParam("transferId") String transferId, @QueryParam("access_token") String accessToken) throws Exception {
		return null;
	}
}
